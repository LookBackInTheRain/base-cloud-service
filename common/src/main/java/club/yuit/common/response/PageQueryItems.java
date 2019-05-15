package club.yuit.common.response;

import lombok.Data;

import java.util.List;

/**
 * @author yuit
 * @date  2018/8/6 15:56
 *
 **/
@Data
public class PageQueryItems<T> extends Items<T> {

    private Integer currentPage;
    private Integer pageSize;

}
