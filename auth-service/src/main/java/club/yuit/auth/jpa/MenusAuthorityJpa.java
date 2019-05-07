package club.yuit.auth.jpa;


import club.yuit.auth.entity.MenusAuthority;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author liulu
 * @create Time 2018/8/31 14:33
 * @description
 * @modify by
 * @modify time
 **/
public interface MenusAuthorityJpa extends RootJpa<MenusAuthority,String> {

    @Modifying
    @Query(nativeQuery = true,value = "delete from menus_authority where menus_authority.menuId=:menuId")
    int deleteByMenuId(@Param("menuId") String menuId);

    @Modifying
    @Query(nativeQuery = true,value = "delete from menus_authority where menus_authority.authorityId=:authorityId")
    void deleteByAuthorityId(@Param("authorityId") String authorityId);



}
