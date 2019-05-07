package club.yuit.auth.service;


import club.yuit.auth.entity.Role;
import club.yuit.auth.entity.input.role.ModifyRoleInput;
import club.yuit.auth.entity.input.role.RoleAuthorizationInput;
import club.yuit.common.response.BaseResponse;
import club.yuit.common.response.ListResponse;
import club.yuit.common.response.PageAndSortResponse;
import club.yuit.common.response.SimpleResponse;

import java.util.List;

/**
 * @author yuit
 * @create time 2018/8/31
 * @description
 * @modify by
 * @modify time
 **/
public interface RoleService  {

   /**
    * 添加角色
    * @param role
    * @return
    */
   BaseResponse addRole(Role role);

   /**
    * 分页查询角色
    * @param currentPage
    * @param pageSize
    * @return
    */
   PageAndSortResponse pageQueryRoles(Integer currentPage, Integer pageSize);

   /**
    * 通过id获取角色
    * @param id
    * @return
    */
   SimpleResponse getRoleById(String id);

   /**
    * 获取所有角色
    * @return
    */
   ListResponse findAllRoles();

   /**
    * 修改角色
    * @param input
    * @return
    * @throws Exception
    */
   BaseResponse modifyRole(ModifyRoleInput input) throws Exception;

   /**
    * 批量删除角色
    * @param ids
    * @return
    */
   BaseResponse batchDeleteRoles(List<String> ids);

   /**
    * 通过Id删除角色
    * @param id
    * @return
    */
   BaseResponse deleteById(String id);

   /**
    * 查询 Roles 通过 userIds
    * @param ids
    * @return
    */
   SimpleResponse findByUserIds(String ids);

   /**
    * 给角色授权
    * @param input
    * @return
    */
   BaseResponse authorizationToRole(RoleAuthorizationInput input);

}
