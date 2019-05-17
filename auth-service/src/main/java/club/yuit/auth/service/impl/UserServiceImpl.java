package club.yuit.auth.service.impl;

import club.yuit.auth.entity.Role;
import club.yuit.auth.entity.User;
import club.yuit.auth.entity.UserRole;
import club.yuit.auth.entity.input.user.ModifyUserInput;
import club.yuit.auth.entity.input.user.ModifyUserPswInput;
import club.yuit.auth.entity.input.user.UserAuthorizationInput;
import club.yuit.auth.jpa.*;
import club.yuit.auth.service.UserService;
import club.yuit.common.exception.ArgumentsFailureException;
import club.yuit.common.response.*;
import club.yuit.common.utils.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static club.yuit.common.response.HttpResponseUtils.*;


/**
 * @author yuit
 * @date 2019/4/24 11:08
 */
@Service
public class UserServiceImpl implements UserService {

    private UserJpa userJap;
    private UserRoleJpa userRoleJpa;
    private RoleJpa roleJpa;
    private PasswordEncoder encoder;

    private BaseSqlQuery baseSqlQuery;
    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRoleJpa userRoleJpa, UserJpa userJap, RoleJpa roleJpa, PasswordEncoder encoder, BaseSqlQuery baseSqlQuery, PasswordEncoder passwordEncoder) {
        this.userJap = userJap;
        this.userRoleJpa = userRoleJpa;
        this.roleJpa = roleJpa;
        this.encoder = encoder;
        this.baseSqlQuery = baseSqlQuery;
        this.passwordEncoder = passwordEncoder;
    }

    /*public UserServiceImpl() {
    }*/


    @Override
    public User findUserByUsername(String username) {
        return this.userJap.findUserByUsernameOrEmail(username);
    }

    @Override
    public User login(String username) {
        List<User> users = userJap.isExist(username);

        User user = null;

        if (users != null && users.size() == 1) {
            user = users.get(0);

            List<Role> roles = this.roleJpa.findRoleNameByUserId(user.getId());

            List<String> roleNames = new ArrayList<>();

            roles.forEach(item -> {
                roleNames.add(item.getName());
            });

            user.setRoles(roleNames);
        }

        return user;
    }

    @Override
    public SimpleResponse findCurrentUserById(String id) {

        User user = this.userJap.findCurrentUserById(id);

        user.setPassword(null);

        return successSimpleResponse(user);
    }

    /**
     * 根据ID删除用户
     *
     * @return
     */
    @Override
    @Transactional
    public BaseResponse userDelete(String id, String currentKey) {

        if (id.equals(currentKey)) {
            throw new ArgumentsFailureException("不能删除当前登录用户");
        }

        if (userJap.userDelete(id) != 0) {
            return successResponse("删除成功!");
        } else {
            throw new ArgumentsFailureException();
        }
    }

    /**
     * 根据ids批量删除用户
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public BaseResponse userDeleteByIds(List<String> ids, String currentKey) {

        if (ids.contains(currentKey)) {
            throw new ArgumentsFailureException("不能删除当前登录用户");
        }

        if (userJap.userDeleteByIds(ids) != 0) {
            return successResponse("批量删除成功!");
        } else {
            throw new ArgumentsFailureException();
        }
    }

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    @Override
    public SimpleResponse getUser(String id) {
        User user = userJap.findUserById(id);
        if (user != null) {
            return successSimpleResponse("返回成功!", user);
        } else {
            throw new ArgumentsFailureException("没有该用户!");
        }
    }

    /**
     * 添加用户（单个用户）
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public BaseResponse addUser(User user) {
        //先判断数据库中是否存在用户名或者邮箱相同的情况
        if (userJap.isExist(user.getUsername()).size() != 0) {
            throw new ArgumentsFailureException("用户名存在，请重试!");
        } else if (userJap.isExist(user.getEmail()).size() != 0) {
            throw new ArgumentsFailureException("Email存在，请重试!");
        } else {
            User u = new User();
            BeanUtils.copyProperties(user, u);
            u.setPassword(encoder.encode("888888"));
            u.setCreateTime(Timestamp.from(Instant.now()));
            userJap.save(u);
            return successResponse("添加成功!");
        }
    }

    /**
     * 修改用户信息，这里前段传过来的数据是修改的数据，其中为空的，不进行修改。
     *
     * @param userInput
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SimpleResponse userUpdate(ModifyUserInput userInput) throws Exception {
        User user = userJap.findUserById(userInput.getId());

        if (userInput.getEmail() != null) {
            if (userJap.isExist(userInput.getEmail()).size() > 1) {
                throw new ArgumentsFailureException("Email已存在，请重试!");
            }
        }

        if (userInput.getUsername() != null) {
            if (userJap.findByUsernameAndNotEqId(userInput.getUsername(), userInput.getId()) != null) {
                throw new ArgumentsFailureException("用户名已存在！");
            }
        }

        CommonUtils.copy(userInput, user);
        user.setModifyTime(Timestamp.from(Instant.now()));
        userJap.saveAndFlush(user);
        return successSimpleResponse(user);
    }

    @Override
    public PageAndSortResponse searchUserByUsername(String username, Integer currentPage, Integer pageSize) throws Exception {
        String sql = "SELECT * FROM sys_user WHERE username LIKE ?";
        List<String> id = Arrays.asList("%" + username + "%");

        PageQueryItems items = baseSqlQuery.baseSqlPageQuery(sql, id, currentPage, pageSize);
        return successPageResponse(items);
    }

    @Override
    public BaseResponse userPswUpdate(ModifyUserPswInput userInput) throws Exception {
        User user = userJap.findUserById(userInput.getId());

        boolean flg = this.passwordEncoder.matches(userInput.getPassword(), user.getPassword());

        if (!flg) {
            throw new ArgumentsFailureException("旧密码不匹配！");
        }


        CommonUtils.copy(userInput, user);
        user.setPassword(passwordEncoder.encode(userInput.getNewPassword()));
        user.setModifyTime(Timestamp.from(Instant.now()));
        userJap.saveAndFlush(user);
        return successResponse("密码修改成功!");

    }

    /**
     * 分页返回用户信息
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageAndSortResponse getUserPageMessage(Integer currentPage, Integer pageSize) {
        PageQueryItems items = JpaQueryUtils.query(userJap, currentPage, pageSize, OrderType.DESC, "modifyTime");
        return successPageResponse(items);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse authorizationToUser(UserAuthorizationInput input) {

        this.userRoleJpa.deleteByUserId(input.getUserId());

        List<Role> roles = this.roleJpa.findByIds(input.getAuths());

        List<UserRole> userRoles = new ArrayList<>();

        roles.forEach(item -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(input.getUserId());
            userRole.setRoleId(item.getId());
            userRoles.add(userRole);
        });

        this.userRoleJpa.saveAll(userRoles);
        return successResponse("授权成功");
    }


}
