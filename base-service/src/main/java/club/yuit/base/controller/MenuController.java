package club.yuit.base.controller;

import club.yuit.base.service.MenuService;
import club.yuit.common.response.SimpleResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuit
 * @date 2019/5/15 17:14
 **/
@RestController
@RequestMapping("/menus")
public class MenuController {

    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping()
    public SimpleResponse getAllMenusByUser() {

        return this.menuService.getUserMenus("yuit");
    }

}
