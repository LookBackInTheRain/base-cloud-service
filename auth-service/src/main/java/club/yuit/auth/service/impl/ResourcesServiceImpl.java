package club.yuit.auth.service.impl;

import club.yuit.auth.entity.Resources;
import club.yuit.auth.entity.input.resources.ModifyResourcesInput;
import club.yuit.auth.jpa.BaseSqlQuery;
import club.yuit.auth.jpa.ResourcesJpa;
import club.yuit.auth.service.ResourcesService;
import club.yuit.common.response.BaseResponse;
import club.yuit.common.response.PageAndSortResponse;
import club.yuit.common.response.PageQueryItems;
import club.yuit.common.response.SimpleResponse;
import club.yuit.common.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static club.yuit.common.response.HttpResponse.*;


/**
 * @author yuit
 * @create time 2018/8/16
 * @description
 * @modify by
 * @modify time
 **/
@Service
public class ResourcesServiceImpl implements ResourcesService {

    private ResourcesJpa resourcesJpa;

    private BaseSqlQuery baseSqlQuery;

    public ResourcesServiceImpl(ResourcesJpa resourcesJpa, BaseSqlQuery baseSqlQuery) {
        this.resourcesJpa = resourcesJpa;
        this.baseSqlQuery = baseSqlQuery;
    }

    @Override
    public List<Resources> getResourcesByRoleNames(List<String> roleNames) {
        return resourcesJpa.getResourcesByRoleNames(roleNames);
    }

    @Override
    public BaseResponse addResources(Resources resources) {
        this.resourcesJpa.save(resources);
        return successResponse("添加成功");
    }

    @Override
    @Transactional
    public BaseResponse deleteById(String id) {
        this.resourcesJpa.deleteById(id);
        return successResponse("删除成功");
    }

    @Override
    @Transactional
    public BaseResponse modify(ModifyResourcesInput input) throws Exception {
        Resources resources = this.resourcesJpa.findById(input.getId()).get();
        CommonUtils.copy(input,resources);
        this.resourcesJpa.save(resources);
        return successResponse("修改成功");
    }

    @Override
    public SimpleResponse getResourcesById(String id) {
        return successSimpleResponse(this.resourcesJpa.findById(id).get());
    }

    @Override
    public PageAndSortResponse pageResources(Integer currentPage, Integer pageSize, String name) {
        String sql = "select * from resources order by modifyTime desc ";
        List<Object> params = null;
        if(StringUtils.isNotBlank(name)){
            sql="select * from resources where name like ? order by modifyTime desc";
            params = Arrays.asList("%"+name+"%");
        }
        PageQueryItems items= this.baseSqlQuery.baseSqlPageQuery(sql,params,currentPage,pageSize);
        return successPageResponse(items);
    }

    @Override
    public PageAndSortResponse getResourcesByAuthId(String authId, Integer currentPage, Integer pageSize) {

        String sql = "select * from resources where id in(select resourceId from resources_authority where authorityId =?)";
        List<String> id = Arrays.asList(authId);
        PageQueryItems items = baseSqlQuery.baseSqlPageQuery(sql,id,currentPage,pageSize);
        return successPageResponse(items);
    }

    @Override
    public PageAndSortResponse getResourcesByAuthIdNotOwn(String id, Integer currentPage, Integer pageSize) {

        String sql="select * from resources where id not in (select resourceId from resources_authority where authorityId=?) order by modifyTime desc";
        List<String> params = Arrays.asList(id);
        PageQueryItems items = baseSqlQuery.baseSqlPageQuery(sql,params,currentPage,pageSize);
        return successPageResponse(items);
    }

    @Override
    @Transactional
    public BaseResponse batchDeleteByIds(List<String> ids) {
        this.resourcesJpa.batchDeleteByIds(ids);
        return successResponse("删除成功");
    }


}
