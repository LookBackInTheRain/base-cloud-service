package club.yuit.auth.jpa;


import club.yuit.auth.entity.Resources;
import club.yuit.common.support.LongSQLQuery;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author yuit
 * @create time 2018/8/16
 * @description
 * @modify by
 * @modify time
 **/
public interface ResourcesJpa extends RootJpa<Resources,String> {

    @Query(value = LongSQLQuery.QUERY_RESOURCES_BY_ROLE_NAMES,nativeQuery = true)
    List<Resources> getResourcesByRoleNames(List<String> roleNames);

    @Modifying
    @Query("delete from Resources rs where rs.id in (:ids)")
    int batchDeleteByIds(@Param("ids") List<String> ids);

}
