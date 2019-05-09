package club.yuit.auth.controller;

import club.yuit.auth.service.UserService;
import club.yuit.auth.support.CheckPermission;
import club.yuit.common.response.HttpResponse;
import club.yuit.common.response.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

@RestController
public class MainController {


    //private ResourceServerTokenServices tokenServices;

    private UserService userService;

    private CheckPermission checkPermission;

    public MainController(
           // ResourceServerTokenServices tokenServices,
            UserService userService,
            CheckPermission checkPermission) {
       // this.tokenServices = tokenServices;
        this.userService = userService;
        this.checkPermission = checkPermission;
    }



    @PostMapping("/token/check")
    public void check(@RequestParam("token") String token, HttpServletRequest request,
                                HttpServletResponse response){

       String uri= request.getRequestURI();

       String method = request.getMethod();

     //  OAuth2AccessToken auth2AccessToken= tokenServices.readAccessToken(token);



        /*if (auth2AccessToken == null) {
            throw new InvalidTokenException("Token was not recognised");
        }

        if (auth2AccessToken.isExpired()) {
            throw new InvalidTokenException("Token has expired");
        }
*/
        //OAuth2Authentication auth2Authentication = tokenServices.loadAuthentication(token);

        //checkPermission.check(request,auth2Authentication.getUserAuthentication());



    }



}
