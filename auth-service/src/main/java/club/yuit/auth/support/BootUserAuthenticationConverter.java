package club.yuit.auth.support;

import club.yuit.auth.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yuit
 * @date  2019/5/5  14:52
 *
 * 扩展token中的信息
 *
 **/
@Component
public class BootUserAuthenticationConverter extends DefaultUserAuthenticationConverter {


    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put(USERNAME, authentication.getName());

        User user = (User) authentication.getPrincipal();

        if (user!= null && !user.getRoles().isEmpty()) {
            response.put("roles", user.getRoles() );
        }
        return response;
    }
}
