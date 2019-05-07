package club.yuit.auth.service;


import club.yuit.common.response.BaseResponse;

import java.util.List;

/**
 * @author yuit
 * @create 2018/11/23 14:50
 * @description
 * @modify
 */
public interface MenuAuthorityService {

    BaseResponse saveList(String authId, List<String> menusIds) throws Exception;

}
