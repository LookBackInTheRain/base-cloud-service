package club.yuit.auth.jpa;

import club.yuit.auth.entity.RoleAuthority;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author liulu
 * @create Time 2018/9/6 18:49
 * @description
 * @modify by
 * @modify time
 **/
public interface RoleAuthorityJpa extends RootJpa<RoleAuthority,String > {

    /**
     * 删除Authority
     * @param authorityId
     * @return
     */
    @Modifying
    @Query(nativeQuery = true,value = "delete from role_authority where role_authority.authorityId=:authorityId")
    int deleteByAuthorityId(@Param("authorityId") String authorityId);

    /*@Modifying
    @Query("delete from RoleAuthority r where r.roleId=?1")*/

    /**
     * 通过roleId 删除
     * @param roleId
     * @return
     */
    int deleteByRoleId(String roleId);


}
