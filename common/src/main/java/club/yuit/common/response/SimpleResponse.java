package club.yuit.common.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date  2018/3/30 20:37
 *
 */

public class SimpleResponse<T> extends BaseResponse {

    private T item;

    protected SimpleResponse() {
    }

    protected SimpleResponse(int status, String msg, T item) {
        super(status, msg);
        this.item = item;
    }


    public T getItem() {
        return this.item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
