package club.yuit.auth.support;

import club.yuit.auth.entity.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yuit
 * @date  2019/5/5  14:52
 *
 * 扩展生成token后返回的json信息
 *
 **/
@Component
public class BootTokenEnhancer implements TokenEnhancer {


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        User user= (User) authentication.getUserAuthentication().getPrincipal();

        Map<String,Object> info =new LinkedHashMap<>();
        info.put("sub",user.getUsername());

        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);

        return accessToken;
    }


}
