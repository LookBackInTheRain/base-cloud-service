package club.yuit.gateway.support;

import club.yuit.common.response.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;

/**
 * @author yuit
 * @date 2019/5/8 15:09
 **/
@Component
public class RemoteTokenService {

    private RestTemplate restTemplate;
    private LoadBalancerClient balancerClient;
    private Logger logger = LoggerFactory.getLogger(RemoteTokenService.class);


    public RemoteTokenService(RestTemplate restTemplate,
                              LoadBalancerClient balancerClient) {
        this.restTemplate = restTemplate;
        this.balancerClient = balancerClient;
    }

    public void loadAuthentication(ServerWebExchange exchange,String accessToken,String checkTokenUrl){

        ServiceInstance instance=this.balancerClient.choose("auth-service");

        logger.info("serviceInstanceId:{}",instance.getInstanceId());
        logger.info("ServiceId:{}",instance.getServiceId());

       String  tokenPath= String.format("http://%s/auth/api/v1/%s?token=%s",instance.getServiceId(),checkTokenUrl,accessToken);


        SimpleResponse result=  restTemplate.postForEntity(tokenPath, null,SimpleResponse.class).getBody();


        System.out.println(result);

    }

    private SimpleResponse postForResponse(String path){
        SimpleResponse response=null;
        try {
            response=this.restTemplate.postForEntity(path, null,SimpleResponse.class).getBody();
        }catch (HttpServerErrorException ex2 ){
                ex2.fillInStackTrace();
        }catch (Exception ex1){
            ex1.fillInStackTrace();
        }

        return response;
    }




}
