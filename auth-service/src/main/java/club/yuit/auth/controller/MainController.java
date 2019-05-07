package club.yuit.auth.controller;

import club.yuit.auth.entity.Client;
import club.yuit.auth.jpa.ClientJpa;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MainController {

    private ClientJpa clientJpa;

    public MainController(ClientJpa clientJpa) {
        this.clientJpa = clientJpa;
    }


    @GetMapping(value = "/")
    public String index(){

        return "index";
    }

    @GetMapping("/other")
    public String other(){

        Client client = this.clientJpa.findClientByClientId("sys_base_manager");

        return "other";
    }


    @GetMapping("/check")
    public void check(HttpServletRequest request,OAuth2Authentication authentication){

       String uri= request.getRequestURI();

       String method = request.getMethod();



    }

}
