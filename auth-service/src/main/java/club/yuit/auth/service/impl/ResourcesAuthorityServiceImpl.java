package club.yuit.auth.service.impl;

import club.yuit.auth.entity.ResourcesAuthority;
import club.yuit.auth.jpa.ResourceAuthorityJpa;
import club.yuit.auth.service.ResourcesAuthorityService;
import club.yuit.common.exception.ArgumentsFailureException;
import club.yuit.common.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static club.yuit.common.response.HttpResponse.successResponse;


/**
 * @author yuit
 * @create 2018/11/20 17:48
 * @description
 * @modify
 */
@Service
public class ResourcesAuthorityServiceImpl implements ResourcesAuthorityService {

    @Autowired
    private ResourceAuthorityJpa resourceAuthorityJpa;

    @Override
    public BaseResponse saveList(List<String> resources, String authId)  throws Exception{

        List<ResourcesAuthority> params = new ArrayList<>();

        if(resources.size()<1){
            throw new ArgumentsFailureException();
        }

        resources.forEach(item->{
            ResourcesAuthority authority = new ResourcesAuthority();
            authority.setAuthorityId(authId);
            authority.setResourceId(item);
            params.add(authority);
        });

        this.resourceAuthorityJpa.saveAll(params);

        return successResponse("添加成功");

    }

    @Override
    @Transactional
    public BaseResponse remove(List<String> resources, String authId) throws Exception {
        if(resources.size()<1){
            throw new ArgumentsFailureException();
        }

        this.resourceAuthorityJpa.deleteByAuthIdAndResourceIds(authId,resources);
        return successResponse("移除成功");

    }
}
