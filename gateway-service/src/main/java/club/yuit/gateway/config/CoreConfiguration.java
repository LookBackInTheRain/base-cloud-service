package club.yuit.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author yuit
 * @date 2019/5/8 9:51
 **/
@Configuration
public class CoreConfiguration  {

    @Autowired
    private LoadBalancerExchangeFilterFunction lbFunction;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }

    @Bean
    @LoadBalanced
    public PathMatcher pathMatcher (){
        return new AntPathMatcher();
    }


    @Bean
    public WebClient webClient(){
        return  WebClient.builder()
                .filter(lbFunction)
                .build();
    }


}


