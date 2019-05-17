package club.yuit.auth.service.impl;

import club.yuit.auth.entity.Menu;
import club.yuit.auth.entity.User;
import club.yuit.auth.entity.input.user.MenuAuthInput;
import club.yuit.auth.entity.input.user.ModifyMenusInput;
import club.yuit.auth.jpa.MenusAuthorityJpa;
import club.yuit.auth.jpa.MenusJpa;
import club.yuit.auth.jpa.UserJpa;
import club.yuit.auth.service.MenusService;
import club.yuit.common.exception.ArgumentsFailureException;
import club.yuit.common.response.BaseResponse;
import club.yuit.common.response.Items;
import club.yuit.common.response.ListResponse;
import club.yuit.common.response.SimpleResponse;
import club.yuit.common.utils.CommonUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static club.yuit.common.response.HttpResponseUtils.*;


//import edu.gzu.mysql.entity.output.menu.MenuDetails;

/**
 * @author liulu
 * @create Time 2018/8/15 19:54
 * @description
 * @modify by
 * @modify time
 **/
@Service
public class MenusServiceImpl implements MenusService {

    MenusJpa menusJpa;

    MenusAuthorityJpa menusAuthorityJpa;

    private UserJpa userJpa;

    public MenusServiceImpl(MenusJpa menusJpa, MenusAuthorityJpa menusAuthorityJpa, UserJpa userJpa) {
        this.menusJpa = menusJpa;
        this.menusAuthorityJpa = menusAuthorityJpa;
        this.userJpa = userJpa;
    }

    /**
     * 获取所有菜单
     *
     * @return
     */
    @Override
    public SimpleResponse getUserMenus(String id) {
        Map<String, Object> mapJSON = new HashMap<>();
        Map<String, Object> mapAPP = new HashMap<>();
        mapAPP.put("name", "监管系统");
        mapAPP.put("description", "基础管理系统");

        User user=this.userJpa.findUserById(id);


        Map<String, Object> mapUser = new HashMap<>(16);
        mapUser.put("name", user.getUsername());
        mapUser.put("avatar", "/assets/tmp/img/avatar.jpg");
        mapUser.put("email", user.getEmail());
        mapUser.put("key", user.getId());

        List<Menu> menusList = menusJpa.getAllMenusId(user.getUsername());
        List<Menu> sortMenus = new ArrayList<>();

        Long begin = System.currentTimeMillis();
        menusList.stream().filter(it->(it.getParentId()==null||it.getParentId().trim().equals(""))).forEach(item->{
            sortMenus.add(this.sort(item,menusList));
        });
        Long end = System.currentTimeMillis();

        System.out.println("time : "+(end-begin));

        mapJSON.put("app", mapAPP);
        mapJSON.put("user", mapUser);
        mapJSON.put("menu", sortMenus);
        return successSimpleResponse("返回成功", mapJSON);
    }

    /**
     * 根据level返回菜单信息
     *
     * @param
     * @return
     */
    @Override
    public SimpleResponse getMenusByLevel(int level) {
        return successSimpleResponse(menusJpa.getMenusByLevel(level));
    }

    /**
     * 添加菜单信息并添加权限
     *
     * @param menuAuthInput
     * @return
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public BaseResponse menusAdd(MenuAuthInput menuAuthInput) {
        Menu m = new Menu();
        try {
            CommonUtils.copy(menuAuthInput, m);
        } catch (Exception e) {
            e.printStackTrace();
        }
        m.setCreateTime(new Date());
        m.setGroup(false);
        menusJpa.save(m);
        return successResponse("添加成功");
    }

    /**
     * 删除菜单信息和相关信息
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public BaseResponse menusDelete(String id) {
        menusAuthorityJpa.deleteByMenuId(id);
        menusJpa.menusDelete(id);
        return successResponse("删除成功!");
    }

    /**
     * 批量删除菜单信息和相关信息
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public BaseResponse menusDeleteByIds(List<String> ids) {
        for (String id : ids) {
            System.out.println(menusAuthorityJpa.deleteByMenuId(id));
        }
        if (menusJpa.menusDeleteByIds(ids) != 0) {
            return successResponse("批量删除成功!");
        } else {
            throw new ArgumentsFailureException();
        }
    }

    /**
     * 修改菜单基本信息
     *
     * @param menusInput
     * @return
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public BaseResponse menusUpdate(ModifyMenusInput menusInput) {
        Menu menus = menusJpa.findMenusById(menusInput.getId());
        try {
            CommonUtils.copy(menusInput, menus);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(menusInput.getParentId()==null){
            menus.setParentId(null);
        }

        menusJpa.saveAndFlush(menus);
        return successResponse("修改成功!");
    }

    @Override
    public ListResponse getAllMenus() {

        List<Menu> items = this.menusJpa.findSortMenus();

        List<Menu> sortMenus = this.sortMenus(items);

        return successListResponse(new Items<Menu>(items.size(),sortMenus));
    }

    @Override
    public ListResponse findByAuthId(String authId) {
        List<Menu> menus = this.menusJpa.findByAuthId(authId);
        return successListResponse(new Items<Menu>(menus.size(),menus));
    }

    private Menu sort(Menu parent , List<Menu> menusList){

        menusList.forEach(item->{
            if(item.getParentId()!=null&&item.getParentId().equals(parent.getId())){
                parent.getChildren().add(item);
                // 递归
                sort(item,menusList);
            }
        });

        return  parent;
    }

    private List<Menu> sortMenus(List<Menu> params){
        List<Menu> sortMenus = new ArrayList<>();

        params.stream().filter(it->(it.getParentId()==null||it.getParentId().trim().equals(""))).forEach(item->{
            sortMenus.add(this.sort(item,params));
        });

        return sortMenus;
    }

}
