package club.yuit.auth.support;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static club.yuit.common.response.HttpResponseUtils.baseResponse;

/**
 * @author yuit
 * @date 2018/11/2 10:48
 *
 */
@Component
public class BootOAuth2AuthExceptionEntryPoint extends OAuth2AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        HttpUtils.writerError(baseResponse(HttpStatus.UNAUTHORIZED.value(),e.getMessage()),response);
    }

}
