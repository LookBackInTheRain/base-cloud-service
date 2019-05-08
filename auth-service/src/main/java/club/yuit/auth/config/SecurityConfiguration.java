package club.yuit.auth.config;


import club.yuit.auth.service.BootUserDetailService;
import club.yuit.common.support.BootSecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author yuit
 * @date 2018/10/10  11:48
 **/
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private BootUserDetailService userDetailService;

    private BootSecurityProperties properties;


    public SecurityConfiguration(BootUserDetailService userDetailService, BootSecurityProperties properties) {
        this.userDetailService = userDetailService;
        this.properties = properties;
    }

    /**
     * 让Security 忽略这些url，不做拦截处理
     *
     * @param
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .requestMatchers()
                .antMatchers(properties.getLoginPage(),
                        properties.getLoginProcessUrl()
                        , "/oauth/**")

                .and()
                .authorizeRequests()
                // 自定义页面或处理url是，如果不配置全局允许，浏览器会提示服务器将页面转发多次
                .antMatchers(properties.getLoginPage(), properties.getLoginProcessUrl(), "/favicon.ico")
                .permitAll()
                .anyRequest()
                .authenticated();

        // 表单登录
        http.formLogin()
                //.failureHandler(handler)
                // 页面
                .loginPage(properties.getLoginPage())
                // 登录处理url
                .loginProcessingUrl(properties.getLoginProcessUrl());
        http.httpBasic().disable();

        http.csrf().disable();

    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
