package club.yuit.auth.controller;

import club.yuit.auth.service.MenuAuthorityService;
import club.yuit.common.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yuit
 * @create 2018/11/23 14:57
 * @description
 * @modify
 */
@RestController
@RequestMapping("/menu_authority")
@Api
public class MenuAuthorityController {

    private MenuAuthorityService menuAuthorityService;

    public MenuAuthorityController(MenuAuthorityService menuAuthorityService) {
        this.menuAuthorityService = menuAuthorityService;
    }

    @PostMapping("/{authId}")
    @ApiOperation("对应权限添加菜单")
    public BaseResponse saveList(@PathVariable String authId, @RequestBody List<String> menuIds) throws Exception {
        return this.menuAuthorityService.saveList(authId,menuIds);
    }

}
