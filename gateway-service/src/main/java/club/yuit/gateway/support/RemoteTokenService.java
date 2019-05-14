package club.yuit.gateway.support;

import club.yuit.common.support.PermissionProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
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

    private WebClient webClient;


    public RemoteTokenService(RestTemplate restTemplate,
                              LoadBalancerClient balancerClient,
                              WebClient webClient) {
        this.restTemplate = restTemplate;
        this.balancerClient = balancerClient;
        this.webClient = webClient;
    }

    public Mono<Void> loadAuthentication(ServerWebExchange exchange, String accessToken, String checkTokenUrl, GatewayFilterChain chain) {

        ServiceInstance instance = this.balancerClient.choose("auth-service");

        logger.info("serviceInstanceId:{}", instance.getInstanceId());
        logger.info("ServiceId:{}", instance.getServiceId());

        String tokenPath = String.format("http://%s%s", instance.getServiceId(), checkTokenUrl);

        URI uri = exchange.getRequest().getURI();

       /* Map map = this.postForMap(new PermissionProperties(uri.getPath(),accessToken.trim()),tokenPath);

        if(map==null){
            return;
        }

        if (map.containsKey("ERROR")){
            System.out.println();
        }*/


        Mono<ClientResponse> mono = this.webClient
                .post()
                .uri(tokenPath)
                .header("content-type", "application/json")
                .body(BodyInserters.fromObject(new PermissionProperties(uri.getPath(), accessToken.trim())))
                .exchange();

        return mono.flatMap(res -> {
            System.out.println(res);
                if(res.statusCode()== HttpStatus.OK){
                    return chain.filter(exchange);
                }
                return Mono.error(new BootWebClientResponseException(res.statusCode().value()));
        });
    }


    private Map postForMap(PermissionProperties permission, String tokenPath) {
        Map<String, Object> response = new LinkedHashMap<>();

        try {

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            ObjectMapper mapper = new ObjectMapper();

            String jsonPermission = mapper.writeValueAsString(permission);

            response = this.restTemplate.postForEntity(tokenPath, new HttpEntity<String>(jsonPermission, httpHeaders), Map.class).getBody();
        } catch (HttpServerErrorException serverException) {
            response.put("status", serverException.getStatusCode().value());
            response.put("ERROR", serverException.getMessage());

        } catch (HttpClientErrorException clientException) {
            response.put("status", clientException.getStatusCode().value());
            response.put("ERROR", clientException.getMessage());
        } catch (Exception ex1) {
            response.put("ERROR", ex1.getMessage());
            ex1.fillInStackTrace();
        }

        return response;
    }


}
