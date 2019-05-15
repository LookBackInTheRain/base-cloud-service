package club.yuit.auth.controller;

import club.yuit.auth.service.UserService;
import club.yuit.auth.support.CheckPermission;
import club.yuit.common.exception.ArgumentsFailureException;
import club.yuit.common.support.BootRequestProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestController
public class MainController {


    private DefaultTokenServices tokenServices;

    private UserService userService;

    private CheckPermission checkPermission;

    public MainController(
            DefaultTokenServices tokenServices,
            UserService userService,
            CheckPermission checkPermission) {
        this.tokenServices = tokenServices;
        this.userService = userService;
        this.checkPermission = checkPermission;


    }


    @PostMapping("/token/check")
    @ResponseStatus(HttpStatus.OK)
    public void check(@RequestBody BootRequestProperties requestProperties) {

        OAuth2AccessToken auth2AccessToken = tokenServices.readAccessToken(requestProperties.getToken());

        if (requestProperties.getToken() == null) {
            throw new ArgumentsFailureException();
        }
        if(StringUtils.countMatches(requestProperties.getToken(),".")!=2){
            throw new ArgumentsFailureException();
        }


        if (auth2AccessToken == null) {
            throw new InvalidTokenException("Token was not recognised");
        }

        if (auth2AccessToken.isExpired()) {
            throw new InvalidTokenException("Token has expired");
        }

        OAuth2Authentication auth2Authentication = tokenServices.loadAuthentication(auth2AccessToken.getValue());

        checkPermission.check(requestProperties, auth2Authentication.getUserAuthentication());

    }


}
