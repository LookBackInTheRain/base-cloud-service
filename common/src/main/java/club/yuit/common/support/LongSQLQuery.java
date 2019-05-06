package club.yuit.common.support;

/**
 * @author yuit
 * @create time 2018/8/16
 * @description
 * @modify by
 * @modify time
 **/
public class LongSQLQuery {

    public static final String QUERY_RESOURCES_BY_ROLE_NAMES="select distinct res.* from resources res, ( select resa.resourceId from resources_authority resa, ( select distinct ra.authorityId from role_authority ra, (select id from role   where role.name in (?1)  ) rs  where ra.roleId = rs.id) rs where rs.authorityId = resa.authorityId) rs where rs.resourceId = res.id";

    public static final String QUERY_MENUSID_BY_USER_NAME="SELECT DISTINCT * FROM menus,( select ma.menuId from  menus_authority ma,(select ra.authorityId from role_authority ra,(select ur.roleId from user_role ur, (select id from sys_user where userName =:userName) rs where ur.userId = rs.id) rs where ra.roleId = rs.roleId) rs where ma.authorityId = rs.authorityId) rs WHERE menus.id = rs.menuId order by sort asc ";

    public static final String QUERY_AUTHS_BY_ROLE_ID="select * from authority auths,(select authorityId from role_authority where roleId = ?1) res where auths.id= res.authorityId";

    public static final String QUERY_ROLES_BY_USER_ID="select * from role rls, (select roleId from user_role  where userId = ?1) res where rls.id = res.roleId";
}
