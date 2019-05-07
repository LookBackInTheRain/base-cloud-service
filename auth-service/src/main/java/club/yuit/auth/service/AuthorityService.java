package club.yuit.auth.service;



import club.yuit.auth.entity.Authority;
import club.yuit.common.response.BaseResponse;
import club.yuit.common.response.PageAndSortResponse;
import club.yuit.common.response.SimpleResponse;

import java.util.List;

/**
 * @author liulu
 * @create Time 2018/8/31 13:13
 * @description
 * @modify by
 * @modify time
 **/
public interface AuthorityService {
    /**
     * 查询所有权限
     *
     * @return
     */
    SimpleResponse findAll();

    BaseResponse authorityDelete(String id);

    SimpleResponse getAuthorityById(String id);

    BaseResponse authorityAdd(Authority authority);

    BaseResponse authorityUpdate(Authority authority);

    SimpleResponse findAuthsByRoleId(String id);

    PageAndSortResponse findAuthsPagination(Integer currentPage, Integer pageSize);

    BaseResponse batchDeleteByIds(List<String> ids);

}
