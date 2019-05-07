package club.yuit.auth.jpa;

import club.yuit.auth.entity.Authority;
import club.yuit.common.support.LongSQLQuery;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author liulu
 * @create Time 2018/8/31 13:17
 * @description
 * @modify by
 * @modify time
 **/
public interface AuthorityJpa extends RootJpa<Authority, String> {



    /**
     *  通过id获取权限
     * @param id
     * @return
     */
    Authority findAuthorityById(String id);

    /**
     * 通过 roleId 获取权限
     * @param id
     * @return
     */
    @Query(value = LongSQLQuery.QUERY_AUTHS_BY_ROLE_ID,nativeQuery = true)
    List<Authority> findByRoleId(String id);


    /**
     * 通过dis查询Authority
     * @param ids
     * @return
     */
    @Query("from Authority where id in(?1)")
    List<Authority> findByIds(List<String> ids);

    @Query("delete from Authority where id in (?1)")
    @Modifying
    int batchDeleteByIds(List<String> ids);

}
