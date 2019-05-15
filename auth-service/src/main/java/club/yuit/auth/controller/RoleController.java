package club.yuit.auth.controller;

import club.yuit.auth.entity.Role;
import club.yuit.auth.entity.input.role.ModifyRoleInput;
import club.yuit.auth.entity.input.role.RoleAuthorizationInput;
import club.yuit.auth.service.AuthorityService;
import club.yuit.auth.service.RoleService;
import club.yuit.common.response.BaseResponse;
import club.yuit.common.response.ListResponse;
import club.yuit.common.response.PageAndSortResponse;
import club.yuit.common.response.SimpleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author yuit
 * @create time 2018/8/31
 * @description
 * @modify by
 * @modify time
 **/
@RestController
@RequestMapping("/role")
@Api(description = "角色管理")
public class RoleController {


    private RoleService roleService;

    private AuthorityService authorityService;

    public RoleController(RoleService roleService, AuthorityService authorityService) {
        this.roleService = roleService;
        this.authorityService = authorityService;
    }

    @PostMapping()
    @ApiOperation("添加角色")
    public BaseResponse addRole(
            @RequestBody @Valid Role role) {
        return this.roleService.addRole(role);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除角色")
    public BaseResponse deleteRole(@PathVariable String id) {
        return this.roleService.deleteById(id);
    }

    @DeleteMapping
    @ApiOperation("比量删除角色")
    public BaseResponse bathDelete(
            @RequestBody List<String> ids) {
        return this.roleService.batchDeleteRoles(ids);
    }

    @PutMapping
    @ApiOperation("修改角色信息")
    public BaseResponse modifyRole(@Valid @RequestBody ModifyRoleInput input) throws Exception {
        return this.roleService.modifyRole(input);
    }


    @GetMapping
    @ApiOperation("获取所有角色")
    public ListResponse getAllRoles() {
        return this.roleService.findAllRoles();
    }

    @GetMapping("/page/{currentPage}/{pageSize}")
    @ApiOperation("分页获取角色")
    public PageAndSortResponse pageQueryRole(
            @PathVariable Integer currentPage,
            @PathVariable Integer pageSize) {
        return this.roleService.pageQueryRoles(currentPage, pageSize);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据Id获取角色信息")
    public SimpleResponse getRoleById(
            @PathVariable String id) {
        return this.roleService.getRoleById(id);
    }


    @GetMapping("/users/{id}")
    @ApiOperation("通过用户Id获取角色")
    public SimpleResponse getRolesByUserId(@PathVariable String id) {
        return this.roleService.findByUserIds(id);
    }


    @PostMapping("/auths")
    @ApiOperation("给角色授权")
    public BaseResponse authorizationToRole(@Valid @RequestBody RoleAuthorizationInput input) {
        return this.roleService.authorizationToRole(input);
    }


}
