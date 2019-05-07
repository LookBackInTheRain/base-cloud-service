package club.yuit.auth.service;

import club.yuit.auth.entity.Resources;
import club.yuit.auth.entity.input.resources.ModifyResourcesInput;
import club.yuit.common.response.BaseResponse;
import club.yuit.common.response.PageAndSortResponse;
import club.yuit.common.response.SimpleResponse;

import java.util.List;

/**
 * @author yuit
 * @create time 2018/8/16
 * @description
 * @modify by
 * @modify time
 **/
public interface ResourcesService {

    List<Resources> getResourcesByRoleNames(List<String> roleNames);

    BaseResponse addResources(Resources resources);

    BaseResponse deleteById(String id);

    BaseResponse modify(ModifyResourcesInput input) throws Exception;

    SimpleResponse getResourcesById(String id);

    PageAndSortResponse pageResources(Integer currentPage, Integer pageSize, String name);

    PageAndSortResponse getResourcesByAuthId(String authId, Integer currentPage, Integer pageSize);

    PageAndSortResponse getResourcesByAuthIdNotOwn(String id, Integer currentPage, Integer pageSize);


    BaseResponse batchDeleteByIds(List<String> ids);


}
