package club.yuit.auth.jpa;

import club.yuit.common.response.PageQueryItems;

import java.util.List;
import java.util.Map;

/**
 * @Author yuit
 * @Date: Create in 2018/5/1 17:10
 * @Description
 * @Modified By:
 */
public interface BaseSqlQuery {

    /**
     * 原生分页查询
     * @param sql
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageQueryItems baseSqlPageQuery(String sql, List<? extends Object> parameters, Integer currentPage, Integer pageSize);

    /**
     *
     * @param sql
     * @param parameters
     * @return
     */
    List<Map<String,Object>> taskQuery(String sql, List<Object> parameters);

}
