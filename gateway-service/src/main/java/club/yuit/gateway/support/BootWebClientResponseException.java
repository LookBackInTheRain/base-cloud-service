package club.yuit.gateway.support;

import lombok.Getter;

/**
 * @author yuit
 * @date 2019/5/14 17:55
 **/
@Getter
public class BootWebClientResponseException extends RuntimeException  {

    private int status;

    public BootWebClientResponseException(int status) {
        this.status = status;
    }

    public BootWebClientResponseException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BootWebClientResponseException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = status;
    }

    public BootWebClientResponseException(Throwable cause, int status) {
        super(cause);
        this.status = status;
    }

    public BootWebClientResponseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int status) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }
}
