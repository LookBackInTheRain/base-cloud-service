
package club.yuit.auth.controller;

import club.yuit.auth.entity.Authority;
import club.yuit.auth.service.AuthorityService;
import club.yuit.common.response.BaseResponse;
import club.yuit.common.response.PageAndSortResponse;
import club.yuit.common.response.SimpleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author liulu
 * @create Time 2018/8/15 19:58
 * @description
 * @modify by
 * @modify time
 */
@Api(description = "权限管理")
@RequestMapping("authority")
@RestController
public class AuthorityController {

    AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping()
    @ApiOperation(value = "获取所有权限")
    public SimpleResponse getAllAuthority() {
        return authorityService.findAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据Id删除权限")
    @ApiImplicitParam(name = "id", value = "权限id", dataType = "String")
    public BaseResponse authorityDelete(@PathVariable String id) {
        return authorityService.authorityDelete(id);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取权限信息")
    @ApiImplicitParam(name = "id", value = "权限id", dataType = "String")
    public SimpleResponse getAuthorityById(@PathVariable String id) {
        return authorityService.getAuthorityById(id);
    }

    @PostMapping()
    @ApiOperation(value = "添加权限")
    @ApiImplicitParam(name = "authority", value = "增加的权限信息", dataType = "Authority")
    public BaseResponse authorityAdd(@RequestBody Authority authority) {
        return authorityService.authorityAdd(authority);
    }

    @PutMapping()
    @ApiOperation(value = "修改权限")
    @ApiImplicitParam(name = "authority", value = "修改的权限信息", dataType = "Authority")
    public BaseResponse authorityUpdate(@RequestBody Authority authority) throws Exception {
        return authorityService.authorityUpdate(authority);
    }

    @DeleteMapping("/batch")
    @ApiOperation("批量删除")
    public BaseResponse batchDeleteAuthsByIds(@RequestBody List<String> ids){
        return this.authorityService.batchDeleteByIds(ids);
    }


    @GetMapping("/roles/{id}")
    @ApiOperation("根据roleId获取权限")
    public SimpleResponse findAuthsByRoleId(@PathVariable String id) {
        return this.authorityService.findAuthsByRoleId(id);
    }

    @GetMapping("/page/{currentPage}/{pageSize}")
    @ApiOperation("分页获取权限信息")
    public PageAndSortResponse findAuthsPagination(@PathVariable Integer currentPage, @PathVariable Integer pageSize){
        return this.authorityService.findAuthsPagination(currentPage,pageSize);
    }


}

