package club.yuit.gateway.support;


import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.*;

import static club.yuit.common.response.HttpResponse.baseResponse;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author yuit
 * @date 2019/5/5 17:32
 * 全局异常处理
 **/
public class BootExceptionHandler implements ErrorWebExceptionHandler {

    private final ErrorAttributes errorAttributes;
    private List<ViewResolver> viewResolvers = Collections.emptyList();
    private List<HttpMessageWriter<?>> messageWriters = Collections.emptyList();
    private List<HttpMessageReader<?>> messageReaders = Collections.emptyList();

    public void setMessageReaders(List<HttpMessageReader<?>> messageReaders) {
        this.messageReaders = messageReaders;
    }

    public void setViewResolvers(List<ViewResolver> viewResolvers) {
        this.viewResolvers = viewResolvers;
    }

    public void setMessageWriters(List<HttpMessageWriter<?>> messageWriters) {
        this.messageWriters = messageWriters;
    }

    public BootExceptionHandler(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }


    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {

        this.errorAttributes.storeErrorInformation(ex, exchange);

        ServerRequest request = ServerRequest.create(exchange, Collections.emptyList());

        int status = 0;

        String msg="";

        // 找不到服务
        if(ex instanceof NotFoundException ){
            status=HttpStatus.BAD_REQUEST.value();
            msg= HttpStatus.BAD_REQUEST.getReasonPhrase();
        }else if (ex instanceof ResponseStatusException){
            status = ((ResponseStatusException) ex).getStatus().value();
            msg = ex.getMessage();
        }else {
            status = HttpStatus.INTERNAL_SERVER_ERROR.value();
            msg= HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }

        return routerFunction(status, msg)
                .route(request)
                .flatMap(handler -> handler.handle(request))
                .flatMap(res ->
                        {
                            exchange.getResponse().getHeaders()
                                    .setContentType(res.headers().getContentType());
                            return res.writeTo(exchange, new ResponseContext());
                        }
                );
    }


    private RouterFunction<ServerResponse> routerFunction(int status, String msg) {
        return route(t -> true, (handler) -> {

            return ServerResponse.status(status)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(BodyInserters.fromObject(baseResponse(status,msg)));

        });
    }


    private class ResponseContext implements ServerResponse.Context {

        @Override
        public List<HttpMessageWriter<?>> messageWriters() {
            return BootExceptionHandler.this.messageWriters;
        }

        @Override
        public List<ViewResolver> viewResolvers() {
            return BootExceptionHandler.this.viewResolvers;
        }

    }


}
