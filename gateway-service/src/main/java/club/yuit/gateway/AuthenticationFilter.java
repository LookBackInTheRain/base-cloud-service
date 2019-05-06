package club.yuit.gateway;

import jdk.nashorn.internal.parser.TokenType;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @author yuit
 * @date 2019/5/5 16:58
 **/
@Component
public class AuthenticationFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        System.out.println("----------------------------------------->");

        //获取request
        ServerHttpRequest request = exchange.getRequest();
        // 获取 request header
        HttpHeaders headers=request.getHeaders();

        String token = null;


        if(headers.containsKey(HttpHeaders.AUTHORIZATION)&&
                headers.get(HttpHeaders.AUTHORIZATION).size()==1){
            token = headers.get(HttpHeaders.AUTHORIZATION).get(0);

            String tokenType=token.substring(0, 6);
        }






        URI uri=exchange.getRequest().getURI();
        String path = uri.getPath();

        String path_1=exchange.getRequest().getPath().toString();

        return chain.filter(exchange);
    }


}
