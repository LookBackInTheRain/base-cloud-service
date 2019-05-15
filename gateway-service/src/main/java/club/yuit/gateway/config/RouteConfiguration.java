package club.yuit.gateway.config;


import club.yuit.gateway.support.BootExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class RouteConfiguration {


    private final List<ViewResolver> viewResolvers;

    private final ServerCodecConfigurer serverCodecConfigurer;

    public RouteConfiguration(ObjectProvider<ViewResolver> viewResolversProvider, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.orderedStream().collect(Collectors.toList());
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    /**
     * 配置路由策略
     *
     * @param builder
     * @return
     */
   /* @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/resources-service/api/v1")
                        .uri("http://locahost:8001/resources/api/v1")
                )
                .route(p -> p
                        .path("/auth/api/v1/**")
                        .uri("http://localhost:8000/auth/api/v1")
                )
                .build();
    }*/


    @Bean
    public RouterFunction<ServerResponse> routerFunction() {

        return RouterFunctions.route(GET("/test"),
                (handler) -> {

                    ServerResponse.BodyBuilder bodyBuilder = ServerResponse.status(HttpStatus.OK);
                    bodyBuilder.contentType(MediaType.APPLICATION_JSON);

                    Mono<List> bodyData = Mono.just(Arrays.asList("123qwe", "uuuuu", "yuit"));

                    return bodyBuilder.body(bodyData, List.class);
                }
        );
    }


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public BootExceptionHandler exceptionHandler(ErrorAttributes errorAttributes){
        BootExceptionHandler handler = new BootExceptionHandler(errorAttributes);
        handler.setMessageWriters(serverCodecConfigurer.getWriters());
        handler.setViewResolvers(this.viewResolvers);
        //handler.setMessageReaders(serverCodecConfigurer.getReaders());
        return handler;
    }

}
