package club.yuit.auth.support;

import club.yuit.auth.entity.Resources;
import club.yuit.auth.entity.User;
import club.yuit.auth.service.ResourcesService;
import club.yuit.common.exception.NotAuthException;
import club.yuit.common.exception.NotAuthorityException;
import club.yuit.common.support.BootRequestProperties;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
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
     * @param user
     * @return
     */
    public void check(BootRequestProperties requestProperties, User user) {



        boolean isHave = false;



        // 请求uri
        String path = requestProperties.getUrl();


        /*
         * 是否具有系统管理员角色
         */
        if (this.isSysAdmin(user.getRoles())) {
            return;
        }

        // 请求uri
        //String path = request.getRequestURI();
        // 请求方法
        String method = requestProperties.getMethod();

        AntPathMatcher pathMatcher = new AntPathMatcher();

        List<String> roles = user.getRoles();

        if (roles.size() == 0) {
            throw new NotAuthorityException();
        }

        List<Resources> resources = this.resourcesService.getResourcesByRoleNames(roles);

        for (Resources item : resources) {

            String requestUrl = item.getUrl();

            // 判断用户请求的URL中，该用户是否有权限访问，并且判断是否是统一的请求方法和判断serviceId是否正确
            if (pathMatcher.match(requestUrl, path) &&
                    method.equals(item.getMethod()) &&
                    StringUtils.equalsIgnoreCase(requestProperties.getServerId(), item.getServiceId())) {
                isHave = true;
                break;
            }
        }

        if (!isHave) {
            throw new NotAuthorityException();
        }

    }





    /**
     * 判断是否具有系统管理员角色
     *
     * @param roles
     * @return
     */
    private boolean isSysAdmin(List<String> roles) {
        boolean sys_admin = false;
        for (String item : roles) {
            if ("SYS_ADMIN".equals(item)) {
                sys_admin = true;
            }
        }


        return sys_admin;
    }

}
