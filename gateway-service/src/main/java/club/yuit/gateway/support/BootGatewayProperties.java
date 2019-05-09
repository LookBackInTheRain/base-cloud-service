package club.yuit.gateway.support;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author yuit
 * @date 2019/5/9 15:14
 **/
@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "boot.gateway")
public class BootGatewayProperties {

    private String checkTokenUrl="/auth/api/v1/token/check";
    private String authServerId="auth-service";
    private String tokenUrl="/auth/api/v1/oauth/token";
    private List<String> permitUrls;

}




