package club.yuit.auth.jpa;

import club.yuit.auth.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @author yuit
 * @date 2019/4/24 10:57
 */
public interface UserJpa extends RootJpa<User,String> {

    @Query("from User u where u.username=:usernameOrEmail or u.email=:usernameOrEmail")
    User findUserByUsernameOrEmail(String usernameOrEmail);

    @Query("select u from User u where u.email=:userNameOrEmail or u.username=:userNameOrEmail ")
    List<User> isExist(@Param("userNameOrEmail") String userNameOrEmail);


    @Query(nativeQuery = true, value = "select  DISTINCT role.* from role,user_role where role.id=user_role.roleId and user_role.userId=:id")
    List<Map<String, Object>> findUserRole(@Param("id") String id);

    @Modifying
    @Query("delete from User u where u.id=:id")
    int userDelete(@Param("id") String id);

    @Modifying
    @Query(value = "delete from User u where u.id in (:ids) ")
    int userDeleteByIds(@Param("ids") List<String> ids);

    User findUserById(String id);

    @Query("from User where username =:username and id <>:id")
    User findByUsernameAndNotEqId(String username,String id);


    @Query("from User u where u.id=:key")
    User findCurrentUserById(String key);

}
