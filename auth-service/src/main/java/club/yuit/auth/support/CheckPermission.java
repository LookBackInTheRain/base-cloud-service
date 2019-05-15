package club.yuit.auth.support;

import club.yuit.auth.entity.Resources;
import club.yuit.auth.entity.User;
import club.yuit.auth.service.ResourcesService;
import club.yuit.common.exception.NotAuthException;
import club.yuit.common.exception.NotAuthorityException;
import club.yuit.common.support.BootRequestProperties;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author yuit
 * @create 2018/10/29 9:26
 * @description
 * @modify
 */
@Component
public class CheckPermission {



    private ResourcesService resourcesService;

    public CheckPermission(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    /**
     * 检查是否具有访问权限
     *
     * @param requestProperties
     * @param authentication
     * @return
     */
    public void check(BootRequestProperties requestProperties, Authentication authentication) {

        Object obj = authentication.getPrincipal();

        boolean isHave = false;

        /*
         * 判断是否是匿名用户
         */
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new NotAuthException();
        }

        String username = null;

        if (obj instanceof String){
            username = (String) obj;
        }else if (obj instanceof User){
            username = ((User) obj).getUsername();
        }

        // 请求uri
        String path = requestProperties.getUrl();


        /*
         * 是否具有系统管理员角色
         */
        if (this.isSysAdmin(authentication.getAuthorities())) {
            return;
        }

        // 请求uri
        //String path = request.getRequestURI();
        // 请求方法
        String method = requestProperties.getMethod();

        AntPathMatcher pathMatcher = new AntPathMatcher();

        List<String> roles = this.transform(authentication.getAuthorities());

        if (roles.size() == 0) {
            throw new NotAuthorityException();
        }

        List<Resources> resources = this.resourcesService.getResourcesByRoleNames(roles);

        for (Resources item : resources) {

            String requestUrl = item.getUrl();

            // 判断用户请求的URL中，该用户是否有权限访问，并且判断是否是统一的请求方法
            if (pathMatcher.match(requestUrl, path) && method.equals(item.getMethod())) {
                isHave = true;
                break;
            }
        }

        if (!isHave) {
            throw new NotAuthorityException();
        }

    }


    /**
     * 角色转换
     *
     * @param authorities
     * @return
     */
    private List<String> transform(Collection<? extends GrantedAuthority> authorities) {

        List<String> ats = new ArrayList<>();

        authorities.forEach(item -> {
            ats.add(item.getAuthority());
        });

        return ats;
    }


    /**
     * 判断是否具有系统管理员角色
     *
     * @param authorities
     * @return
     */
    private boolean isSysAdmin(Collection<? extends GrantedAuthority> authorities) {
        boolean sys_admin = false;
        for (GrantedAuthority item : authorities) {
            if ("SYS_ADMIN".equals(item.getAuthority())) {
                sys_admin = true;
            }
        }


        return sys_admin;
    }

}
