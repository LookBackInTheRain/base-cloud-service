package club.yuit.auth.jpa;


import club.yuit.auth.entity.ResourcesAuthority;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liulu
 * @create Time 2018/9/6 18:47
 * @description
 * @modify by
 * @modify time
 **/
@Transactional(rollbackFor = Exception.class)
public interface ResourceAuthorityJpa extends RootJpa<ResourcesAuthority,String> {

    @Modifying
    @Query(nativeQuery = true,value = "delete from resources_authority where resources_authority.authorityId=:authorityId")
    int deleteByAuthorityId(@Param("authorityId") String authorityId);


    @Modifying
    @Query(value = "delete from resources_authority where authorityId=?1 and resourceId in (?2)",nativeQuery = true)
    int deleteByAuthIdAndResourceIds(String authId, List<String> resources);
}
