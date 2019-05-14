package club.yuit.gateway.filter;

import club.yuit.gateway.support.BootGatewayProperties;
import club.yuit.gateway.support.RemoteTokenService;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author yuit
 * @date 2019/5/5 16:58
 **/
@Component
public class AuthenticationFilter implements GlobalFilter {


    private RemoteTokenService tokenService;
    private BootGatewayProperties properties;
    private PathMatcher pathMatcher;





    public AuthenticationFilter(RemoteTokenService tokenService,
                                BootGatewayProperties properties,
                                PathMatcher pathMatcher) {
        this.tokenService = tokenService;
        this.properties = properties;
        this.pathMatcher = pathMatcher;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //获取request
        ServerHttpRequest request = exchange.getRequest();
        // 获取 request header
        HttpHeaders headers=request.getHeaders();

        String token = null;

        if (predicate(exchange)){
            if(headers.containsKey(HttpHeaders.AUTHORIZATION)&&
                    headers.get(HttpHeaders.AUTHORIZATION).size()==1){
                token = headers.get(HttpHeaders.AUTHORIZATION).get(0);
                String tokenType=token.substring(0,6);
                token=token.trim().substring(6);
               return this.tokenService.loadAuthentication(exchange,token,properties.getCheckTokenUrl(),chain);
            }
        }

        return chain.filter(exchange);
    }


    private Boolean predicate(ServerWebExchange exchange){

        ServerHttpRequest request=exchange.getRequest();

        String url = request.getURI().getPath();

        if (properties.getCheckTokenUrl().equals(url)||
                properties.getTokenUrl().equals(url)||
                isPermitAllUrl(url)){
            return false;
        }


        return true;
    }

    /**
     * 判断是否为全局允许的url
     * @param url 请求url
     * @return
     */
    private boolean isPermitAllUrl(String url){

        boolean flg= false;

        if (properties.getPermitUrls().size()>0){
            for (String item:properties.getPermitUrls()){
                if (pathMatcher.match(url,item)){
                    flg=true;
                    break;
                }
            }
        }

        return flg;

    }







}
