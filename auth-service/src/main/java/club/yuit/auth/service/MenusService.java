package club.yuit.auth.service;


import club.yuit.auth.entity.input.user.MenuAuthInput;
import club.yuit.auth.entity.input.user.ModifyMenusInput;
import club.yuit.common.response.BaseResponse;
import club.yuit.common.response.ListResponse;
import club.yuit.common.response.SimpleResponse;

import java.util.List;

/**
 * @author liulu
 * @create Time 2018/8/15 19:53
 * @description
 * @modify by
 * @modify time
 **/
public interface MenusService {
    SimpleResponse getUserMenus(String username);

    SimpleResponse getMenusByLevel(int level);

    BaseResponse menusAdd(MenuAuthInput menuAuthInput);

    BaseResponse menusDelete(String id);

    BaseResponse menusDeleteByIds(List<String> ids);

    BaseResponse menusUpdate(ModifyMenusInput menusInput);

    ListResponse getAllMenus();

    ListResponse findByAuthId(String authId);
}
