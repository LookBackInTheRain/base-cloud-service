package club.yuit.auth.config;

import club.yuit.auth.service.BootClientDetailsService;
import club.yuit.auth.service.BootUserDetailService;
import club.yuit.auth.support.BootOAuth2WebResponseExceptionTranslator;
import club.yuit.auth.support.BootTokenEnhancer;
import club.yuit.auth.support.BootUserAuthenticationConverter;
import club.yuit.common.support.BootSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.util.Arrays;

/**
 * @author yuit
 * @date 2019/5/7 13:07
 **/
@Configuration
@EnableAuthorizationServer
public class BootOAuth2Configuration extends AuthorizationServerConfigurerAdapter {


    private AuthenticationManager authenticationManager;


    private BootClientDetailsService clientDetailsService;


    private BootTokenEnhancer tokenEnhancer;


    private AuthenticationEntryPoint authenticationEntryPoint;

    private BootOAuth2WebResponseExceptionTranslator bootWebResponseExceptionTranslator;


    private UserDetailsService userDetailsService;

    private BootUserAuthenticationConverter userAuthenticationConverter;

    private BootSecurityProperties properties;

    @Autowired(required = false)
    public BootOAuth2Configuration(AuthenticationManager authenticationManager,
                                   BootClientDetailsService clientDetailsService,
                                   BootTokenEnhancer tokenEnhancer, AuthenticationEntryPoint authenticationEntryPoint,
                                   BootOAuth2WebResponseExceptionTranslator bootWebResponseExceptionTranslator,
                                   BootUserDetailService userDetailsService, BootUserAuthenticationConverter userAuthenticationConverter,
                                   BootSecurityProperties properties) {
        this.authenticationManager = authenticationManager;
        this.clientDetailsService = clientDetailsService;
        this.tokenEnhancer = tokenEnhancer;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.bootWebResponseExceptionTranslator = bootWebResponseExceptionTranslator;
        this.userDetailsService = userDetailsService;
        this.userAuthenticationConverter = userAuthenticationConverter;
        this.properties = properties;
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {


        // 允许表单登录
        // security.allowFormAuthenticationForClients();

        // 加载client的service
        //filter.setClientDetailsService(clientDetailsService);

        // 自定义异常处理端口
        security.authenticationEntryPoint(authenticationEntryPoint);

        // 认证之前的过滤器
        //security.addTokenEndpointAuthenticationFilter(filter);

        security.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()");




    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置加载客户端的service
        clients.withClientDetails(clientDetailsService);

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                // token 存储方式
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                // 不配置会导致token无法刷新
                .userDetailsService(userDetailsService)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);


        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer, jwtAccessTokenConverter()));

        endpoints.tokenEnhancer(enhancerChain);
        endpoints.accessTokenConverter(jwtAccessTokenConverter());


        // 处理 ExceptionTranslationFilter 抛出的异常
        endpoints.exceptionTranslator(bootWebResponseExceptionTranslator);


        //endpoints.pathMapping("/oauth/check_token","/token/check");

    }


    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }


    @Bean
    @Primary
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();

        accessTokenConverter.setUserTokenConverter(userAuthenticationConverter);

        converter.setAccessTokenConverter(accessTokenConverter);

        converter.setSigningKey(properties.getTokenSigningKey());

        return converter;
    }

    @Bean("tokenService")
    @Primary
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices services=new DefaultTokenServices();
        services.setTokenStore(tokenStore());
        return  services;
    }
}
