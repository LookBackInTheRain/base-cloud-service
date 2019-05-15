package club.yuit.auth.controller;

import club.yuit.auth.service.ResourcesAuthorityService;
import club.yuit.common.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yuit
 * @create 2018/11/20 17:34
 * @description
 * @modify
 */
@RestController()
@RequestMapping("/resources_authority")
@Api
public class ResourcesAuthorityController {

    private ResourcesAuthorityService resourcesAuthorityService;

    public ResourcesAuthorityController(ResourcesAuthorityService resourcesAuthorityService) {
        this.resourcesAuthorityService = resourcesAuthorityService;
    }

    @PostMapping("/{authId}")
    @ApiOperation("为权限添加资源")
    public BaseResponse save(@RequestBody List<String> params, @PathVariable String authId) throws Exception {
        return this.resourcesAuthorityService.saveList(params,authId);
    }

    @PostMapping("/remove/{authId}")
    public BaseResponse remove(@RequestBody List<String> params, @PathVariable String authId) throws Exception {
        return this.resourcesAuthorityService.remove(params,authId);
    }

}
