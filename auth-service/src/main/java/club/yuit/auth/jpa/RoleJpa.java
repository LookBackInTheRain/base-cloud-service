package club.yuit.auth.jpa;

import club.yuit.auth.entity.Role;
import club.yuit.common.support.LongSQLQuery;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author liulu
 * @create Time 2018/8/11 14:24
 * @description
 * @modify by
 * @modify time
 **/
public interface RoleJpa extends RootJpa<Role,String> {

    /**
     * 批量删除role
     * @param ids
     * @return
     */
    @Modifying
    @Query("delete from Role r where r.id in (?1)")
    int batchDeleteRole(List<String> ids);

    /**
     * 查询roles 通过ids
     * @param ids
     * @return
     */
    @Query("from Role where id in (?1)")
    List<Role> findByIds(List<String> ids);

    /**
     * 查询 Roles 通过 userids
     * @param ids
     * @return
     */
    @Query(value = LongSQLQuery.QUERY_ROLES_BY_USER_ID,nativeQuery = true)
    List<Role> findByUserId(String ids);

    @Query(value = "select rls.* from role as rls,(select roleId from user_role where userId = ?1) res where rls.id = res.roleId",nativeQuery = true)
    List<Role> findRoleNameByUserId(String id);

}
