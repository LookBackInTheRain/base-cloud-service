package club.yuit.auth.controller;

import club.yuit.auth.entity.Resources;
import club.yuit.auth.entity.input.resources.ModifyResourcesInput;
import club.yuit.auth.entity.input.user.ModifyMenusInput;
import club.yuit.auth.service.ResourcesService;
import club.yuit.common.response.BaseResponse;
import club.yuit.common.response.PageAndSortResponse;
import club.yuit.common.response.SimpleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author yuit
 * @create time 2018/9/6
 * @description
 * @modify by
 * @modify time
 **/
@RestController
@RequestMapping("/resources")
@Api(description = "资源管理")
public class ResourcesController {

    private ResourcesService resourcesService;

    public ResourcesController(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @PostMapping
    @ApiOperation("添加资源")
    public BaseResponse addResources(@RequestBody @Valid Resources resources) {
        return this.resourcesService.addResources(resources);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除资源")
    public BaseResponse deleteById(@PathVariable String id) {
        return this.resourcesService.deleteById(id);
    }

    @PutMapping
    @ApiOperation("修改资源信息")
    public BaseResponse modify(@RequestBody @Valid ModifyResourcesInput input) throws Exception {
        return this.resourcesService.modify(input);
    }


    @GetMapping("/{id}")
    public SimpleResponse getResourcesById(@PathVariable String id) {
        return this.resourcesService.getResourcesById(id);
    }

    @GetMapping("/page/{currentPage}/{pageSize}")
    @ApiOperation("分页资源信息")
    public PageAndSortResponse page(@PathVariable Integer currentPage, @PathVariable Integer pageSize, String name) {

        return this.resourcesService.pageResources(currentPage, pageSize,name);
    }

    @GetMapping("/auth/{authId}/{currentPage}/{pageSize}")
    @ApiOperation("权限相关的资源")
    public PageAndSortResponse getResourcesByAuthId(@PathVariable String authId,
                                                    @PathVariable Integer currentPage,
                                                    @PathVariable Integer pageSize){
        return this.resourcesService.getResourcesByAuthId(authId,currentPage,pageSize);
    }

    @GetMapping("/auth/not/{authId}/{currentPage}/{pageSize}")
    @ApiOperation("权限相关的资源")
    public PageAndSortResponse getResourcesByAuthIdNotOwn(@PathVariable String authId,
                                                          @PathVariable Integer currentPage,
                                                          @PathVariable Integer pageSize){
        return this.resourcesService.getResourcesByAuthIdNotOwn(authId,currentPage,pageSize);
    }


    @DeleteMapping("/batch")
    public BaseResponse batchDeleteByIds(@RequestBody List<String> ids){
        return this.resourcesService.batchDeleteByIds(ids);
    }


}
