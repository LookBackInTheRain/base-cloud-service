package club.yuit.auth.service.impl;

import club.yuit.auth.entity.Authority;
import club.yuit.auth.entity.Role;
import club.yuit.auth.entity.RoleAuthority;
import club.yuit.auth.entity.input.role.ModifyRoleInput;
import club.yuit.auth.entity.input.role.RoleAuthorizationInput;
import club.yuit.auth.jpa.AuthorityJpa;
import club.yuit.auth.jpa.RoleAuthorityJpa;
import club.yuit.auth.jpa.RoleJpa;
import club.yuit.auth.service.RoleService;
import club.yuit.common.exception.ArgumentsFailureException;
import club.yuit.common.response.*;
import club.yuit.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static club.yuit.auth.jpa.JpaQueryUtils.finAll;
import static club.yuit.auth.jpa.JpaQueryUtils.query;
import static club.yuit.common.response.HttpResponseUtils.*;

/**
 * @author yuit
 * @create time 2018/8/31
 * @description
 * @modify by
 * @modify time
 **/
@Service
public class RoleServiceImpl implements RoleService {


    private RoleJpa roleJpa;


    private RoleAuthorityJpa roleAuthorityJpa;

    private AuthorityJpa authorityJpa;

    @Autowired
    public RoleServiceImpl(RoleJpa roleJpa, RoleAuthorityJpa roleAuthorityJpa, AuthorityJpa authorityJpa) {
        this.roleJpa = roleJpa;
        this.roleAuthorityJpa = roleAuthorityJpa;
        this.authorityJpa = authorityJpa;
    }

    public RoleServiceImpl() {
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @Override
    public BaseResponse addRole(Role role) {
        this.roleJpa.save(role);
        return successResponse("添加成功");
    }

    /**
     * 分页查询角色
     *
     * @param currentPage 当前页
     * @param pageSize    每页的是数据条数
     * @return
     */
    @Override
    public PageAndSortResponse pageQueryRoles(Integer currentPage, Integer pageSize) {
        PageQueryItems items = query(roleJpa, currentPage, pageSize, OrderType.DESC, "modifyTime");
        return successPageResponse(items);
    }

    /**
     * 根据 Id 删除 角色
     *
     * @param id
     * @return
     */
    @Override
    public SimpleResponse getRoleById(String id) {
        Role role = this.roleJpa.findById(id).get();
        return successSimpleResponse(role);
    }

    /**
     * 获取所有的角色信息
     *
     * @return
     */
    @Override
    public ListResponse findAllRoles() {
        Items items=finAll(this.roleJpa);
        return successListResponse(items);
    }

    /**
     * 修改Role 信息
     * @param input
     * @return
     * @throws Exception
     */
    @Override
    public BaseResponse modifyRole(ModifyRoleInput input) throws Exception {
        Role tmp = this.roleJpa.findById(input.getId()).get();
        CommonUtils.copy(input, tmp);
        this.roleJpa.save(tmp);
        return successResponse("修改成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse batchDeleteRoles(List<String> ids) {

        if (ids.size()==0){
            throw new ArgumentsFailureException();
        }

        this.roleJpa.batchDeleteRole(ids);
        return successResponse("删除成功");
    }

    @Override
    public BaseResponse deleteById(String id) {
        this.roleJpa.deleteById(id);
        return successResponse("删除成功");
    }

    @Override
    public SimpleResponse findByUserIds(String id) {
        return successSimpleResponse(this.roleJpa.findByUserId(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse authorizationToRole(RoleAuthorizationInput input) {

        // 先删除后授权，全覆盖方式
        this.roleAuthorityJpa.deleteByRoleId(input.getRoleId());
        List<Authority> authorities = this.authorityJpa.findByIds(input.getAuths());

        List<RoleAuthority> roleAuthorities = new ArrayList<>();

        authorities.forEach(item->{
            RoleAuthority roleAuthority = new RoleAuthority();
            roleAuthority.setRoleId(input.getRoleId());
            roleAuthority.setAuthorityId(item.getId());
            roleAuthorities.add(roleAuthority);
        });
        this.roleAuthorityJpa.saveAll(roleAuthorities);

        return successResponse("授权成功");
    }
}
