package club.yuit.auth.handler;

import club.yuit.common.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;

import static club.yuit.common.response.HttpResponse.baseResponse;

/**
 * @author yuit
 * @date 2019/5/5 17:15
 **/
@RestControllerAdvice
public class BootExceptionHandler {

    /**
     * 404
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse notFoundException(HttpServletResponse response) {
        return baseResponse(404, "找不到服务");
    }


}