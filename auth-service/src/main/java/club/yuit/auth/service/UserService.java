package club.yuit.auth.service;


import club.yuit.auth.entity.User;
import club.yuit.auth.entity.input.user.ModifyUserInput;
import club.yuit.auth.entity.input.user.ModifyUserPswInput;
import club.yuit.auth.entity.input.user.UserAuthorizationInput;
import club.yuit.common.response.BaseResponse;
import club.yuit.common.response.PageAndSortResponse;
import club.yuit.common.response.SimpleResponse;

import java.util.List;

/**
 * @author yuit
 * @date 2019/4/24 11:05
 */
public interface UserService {

    User findUserByUsername(String username);


    /**
     * 登录
     * @param username
     * @return
     */
    User login(String username);


    SimpleResponse findCurrentUserById(String id);



    /**
     * 删除 通过id
     * @param id
     * @return
     */
    BaseResponse userDelete(String id,String currentKey);

    /**
     * 批量删除 通过id
     * @param ids
     * @return
     */
    BaseResponse userDeleteByIds(List<String> ids,String currentKey);
    SimpleResponse getUser(String id);

    /**
     * 添加用户
     * @param user
     * @return
     */
    BaseResponse addUser(User user);

    /**
     * 修改用户信息
     * @param userInput
     * @return
     * @throws Exception
     */
    SimpleResponse userUpdate(ModifyUserInput userInput) throws Exception;


    /**
     * 搜索用户
     * @param username
     * @return
     * @throws Exception
     */
    PageAndSortResponse searchUserByUsername(String username, Integer currentPage, Integer pageSize) throws Exception;


    /**
     * 修改用户密码
     * @param userInput
     * @return
     * @throws Exception
     */
    BaseResponse userPswUpdate(ModifyUserPswInput userInput) throws Exception;



    /**
     * 分页获取用户信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageAndSortResponse getUserPageMessage(Integer currentPage, Integer pageSize);



    /**
     * 用户授权
     * @param input
     * @return
     */
    BaseResponse authorizationToUser(UserAuthorizationInput input);



}
