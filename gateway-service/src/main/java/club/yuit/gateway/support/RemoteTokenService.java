package club.yuit.gateway.support;

import club.yuit.common.support.BootRequestProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
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
    private BootGatewayProperties properties;

    private WebClient webClient;


    public RemoteTokenService(RestTemplate restTemplate,
                              LoadBalancerClient balancerClient,
                              BootGatewayProperties properties, WebClient webClient) {
        this.restTemplate = restTemplate;
        this.balancerClient = balancerClient;
        this.properties = properties;
        this.webClient = webClient;
    }

    public Mono<Void> loadAuthentication(ServerWebExchange exchange, GatewayFilterChain chain, BootRequestProperties requestProperties) {

        String tokenPath = String.format("http://%s%s", properties.getAuthServerId(), properties.getCheckTokenUrl());

        Route route = exchange.getAttribute("org.springframework.cloud.gateway.support.ServerWebExchangeUtils.gatewayRoute");

        requestProperties.setServerId(route.getUri().getHost());

        return  this.webClient
                .post()
                .uri(tokenPath)
                .header("content-type", "application/json")
                .body(BodyInserters.fromObject(requestProperties))
                .exchange()
                .flatMap(res -> {

                    System.out.println("------------------------->"+res.statusCode().value());

                    if(res.statusCode()== HttpStatus.OK){
                        return chain.filter(exchange);
                    }
                    return Mono.error(new BootWebClientResponseException(res.statusCode().value()));
                });
    }

}
