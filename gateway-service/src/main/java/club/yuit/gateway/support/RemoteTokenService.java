package club.yuit.gateway.support;

import club.yuit.common.response.SimpleResponse;
import club.yuit.common.support.PermissionProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;

import java.util.LinkedHashMap;
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

    public void loadAuthentication(ServerWebExchange exchange, String accessToken, String checkTokenUrl) {

        ServiceInstance instance = this.balancerClient.choose("auth-service");

        logger.info("serviceInstanceId:{}", instance.getInstanceId());
        logger.info("ServiceId:{}", instance.getServiceId());

        String tokenPath = String.format("http://%s%s", instance.getServiceId(), checkTokenUrl);


        Map map = this.postForMap(tokenPath, tokenPath);

        if (map.containsKey("ERROR")){
            System.out.println();
        }


        System.out.println(map);

    }

    private Map<String, Object> postForMap(String path, String token) {
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            PermissionProperties permission = new PermissionProperties();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

            ObjectMapper mapper = new ObjectMapper();
            response = this.restTemplate.postForEntity(path, new HttpEntity<String>(mapper.writeValueAsString(permission),httpHeaders), Map.class).getBody();
        } catch (HttpServerErrorException ex2) {
            response.put("ERROR",ex2.getMessage());

            ex2.fillInStackTrace();
        } catch (Exception ex1) {
            response.put("ERROR",ex1.getMessage());
            ex1.fillInStackTrace();
        }

        return response;
    }


}
