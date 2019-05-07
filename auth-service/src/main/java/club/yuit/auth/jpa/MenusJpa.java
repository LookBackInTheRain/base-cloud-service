package club.yuit.auth.jpa;

import club.yuit.auth.entity.Menu;
import club.yuit.common.support.LongSQLQuery;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author yuit
 * @create Time 2018/8/15
 * @description
 * @modify by
 * @modify time
 **/
public interface MenusJpa extends RootJpa<Menu,String> {

    @Query(nativeQuery = true,value = LongSQLQuery.QUERY_MENUSID_BY_USER_NAME)
    List<Menu> getAllMenusId(@Param("userName") String userName);

    @Query(nativeQuery = true,value = "select * from menus where menus.level=:level ")
    List<Menu> getMenusByLevel(int level);

    @Modifying
    @Query("delete from Menu m where m.id=:id")
    int menusDelete(@Param("id") String id);

    @Modifying
    @Query(value = "delete from Menu m where m.id in (:ids) ")
    int menusDeleteByIds(@Param("ids") List<String> ids);

    Menu findMenusById(String id);

    @Query("from Menu m order by m.sort asc ")
    List<Menu> findSortMenus();

    @Query(value = "select * from menus where id in (select menuId from menus_authority where authorityId =?1) and link is not null and trim(link) <> ''",nativeQuery = true)
    List<Menu> findByAuthId(String authId);

}
