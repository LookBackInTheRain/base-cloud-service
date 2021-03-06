package club.yuit.auth.jpa;

import club.yuit.common.exception.ArgumentsFailureException;
import club.yuit.common.response.OrderType;
import club.yuit.common.response.PageQueryItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import club.yuit.common.response.Items;
/**
 * @author yuit
 * @create Time 2018/8/6 15:56
 * @description
 * @modify by
 * @modify time
 **/
public class JpaQueryUtils<T> {


    /**
     * 通用分页查询
     *
     * @param repository
     * @param currentPage
     * @param pageSize
     * @param sortField
     * @param orderType
     * @return
     */
    @SuppressWarnings("unchecked")
    public static PageQueryItems query(JpaRepository repository, Integer currentPage, Integer pageSize, OrderType orderType, String... sortField) {


        Sort sort = null;
        if (orderType == null || orderType == OrderType.ASC) {
            sort = new Sort(Sort.Direction.ASC, sortField);
        } else if (orderType == OrderType.DESC) {
            sort = new Sort(Sort.Direction.DESC, sortField);
        } else {
            throw new ArgumentsFailureException("排序参数错误");
        }



        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sort);

        long count = repository.count();

        Page items = repository.findAll(pageable);

        PageQueryItems query = new PageQueryItems();
        query.setCurrentPage(currentPage);
        query.setPageSize(pageSize);
        query.setCount(count);
        query.setItems(items.getContent());


        return query;

    }

    @SuppressWarnings("unchecked")
    public static <T> Items finAll(JpaRepository repository){
        long count=repository.count();
        List<T> ts=repository.findAll();
        return new Items(count,ts);
    }




}
