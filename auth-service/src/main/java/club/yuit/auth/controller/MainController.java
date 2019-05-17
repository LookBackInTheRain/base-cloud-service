package club.yuit.auth.controller;

import club.yuit.auth.entity.User;
import club.yuit.auth.support.CheckPermission;
import club.yuit.common.exception.ArgumentsFailureException;
import club.yuit.common.response.HttpResponseUtils;
import club.yuit.common.response.SimpleResponse;
import club.yuit.common.support.BootRequestProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class MainController {


    private DefaultTokenServices tokenServices;

    private CheckPermission checkPermission;

    private DiscoveryClient client;

    public MainController(
            DefaultTokenServices tokenServices,
            CheckPermission checkPermission, DiscoveryClient client) {
        this.tokenServices = tokenServices;
        this.checkPermission = checkPermission;


        this.client = client;
    }


    @GetMapping("/services")
    public SimpleResponse serviceIds(){

        List<String> services=this.client.getServices();

        return HttpResponseUtils.successSimpleResponse(services);

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


        List<String> roles= (ArrayList<String>) auth2AccessToken.getAdditionalInformation().get("roles");


        OAuth2Authentication auth2Authentication = tokenServices.loadAuthentication(auth2AccessToken.getValue());

        User user = new User();

        user.setRoles(roles);
        user.setUsername(auth2Authentication.getName());

        checkPermission.check(requestProperties, user);

    }


}
