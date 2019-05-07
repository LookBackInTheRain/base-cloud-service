package club.yuit.auth.jpa;


import club.yuit.auth.entity.UserRole;

/**
 * @author yuit
 * @create Time 2018/8/15
 * @description
 * @modify by
 * @modify time
 **/
public interface UserRoleJpa extends RootJpa<UserRole,String> {

    /**
     * 通过用户Id删除
     * @param id
     * @return
     */
    int deleteByUserId(String id);

}
