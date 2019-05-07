package club.yuit.auth.service;


import club.yuit.common.response.BaseResponse;

import java.util.List;

/**
 * @author yuit
 * @create 2018/11/20 17:36
 * @description
 * @modify
 */
public interface ResourcesAuthorityService {

    /**
     * 添加
     * @param params
     * @param authId
     * @return
     * @throws Exception
     */
    BaseResponse saveList(List<String> params, String authId) throws Exception;

    /**
     * 移除
     * @param params
     * @param authId
     * @return
     */
    BaseResponse remove(List<String> params, String authId) throws Exception;

}
