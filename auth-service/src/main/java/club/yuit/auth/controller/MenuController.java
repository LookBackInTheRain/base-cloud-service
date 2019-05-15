
package club.yuit.auth.controller;


import club.yuit.auth.entity.input.user.MenuAuthInput;
import club.yuit.auth.entity.input.user.ModifyMenusInput;
import club.yuit.auth.service.MenusService;
import club.yuit.common.response.BaseResponse;
import club.yuit.common.response.ListResponse;
import club.yuit.common.response.SimpleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;


/**
 * @author liulu
 * @create Time 2018/8/15 19:58
 * @description
 * @modify by
 * @modify time
 **/
@RestController
@Api(description = "Menus显示")
@RequestMapping("menus")
public class MenuController {

    MenusService menusService;

    public MenuController(MenusService menusService) {
        this.menusService = menusService;
    }

    @GetMapping()
    @ApiOperation(value = "获取所有菜单")
    public SimpleResponse getAllMenusByUser(@ApiIgnore Authentication authentication) {

        if(authentication==null||authentication instanceof AnonymousAuthenticationToken){
            throw new AccessDeniedException("没有身份认证！");
        }

        String  username = authentication.getPrincipal().toString();

        return menusService.getUserMenus(username);
    }

    @GetMapping("/level")
    @ApiOperation(value = "根据level获取菜单")
    public SimpleResponse getFstMenus(int level) {
        return menusService.getMenusByLevel(level);
    }

    @PostMapping()
    @ApiOperation(value = "添加菜单并分配权限")
    public BaseResponse menusAdd(@RequestBody @Valid MenuAuthInput menuAuthInput) {
        return menusService.menusAdd(menuAuthInput);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除菜单", notes = "根据菜单Id删除菜单并删除关联信息")
    @ApiImplicitParam(name = "id", value = "菜单id", dataType = "String")
    public BaseResponse menusDelete(@PathVariable String id) {
        return menusService.menusDelete(id);
    }

    @DeleteMapping("/batch")
    @ApiOperation(value = "批量删除菜单", notes = "根据ids批量删除菜单并删除关联信息")
    @ApiImplicitParam(name = "ids", value = "要删除的id字符串数组", dataType = "List<String>", example = "121,122,123")
    public BaseResponse deleteByIds(@RequestBody List<String> ids) {
        return menusService.menusDeleteByIds(ids);
    }

    @PutMapping()
    @ApiOperation(value = "修改菜单", notes = "修改单个菜单信息")
    @ApiImplicitParam(name = "menusInput", value = "修改的菜单信息", dataType = "ModifyMenusInput")
    public BaseResponse menusUpdate(@RequestBody @Valid ModifyMenusInput menusInput) throws Exception {
        return menusService.menusUpdate(menusInput);
    }

    @GetMapping("/sort")
    @ApiOperation("所有菜单信息")
    public ListResponse getAllMenus() {
        return this.menusService.getAllMenus();
    }

    @GetMapping("/auth/{authId}")
    @ApiOperation("权限对应的菜单")
    public ListResponse findMenusByAuthId(@PathVariable String authId){
        return this.menusService.findByAuthId(authId);
    }

}

