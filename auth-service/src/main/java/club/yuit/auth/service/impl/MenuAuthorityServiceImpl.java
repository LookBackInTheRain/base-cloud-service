package club.yuit.auth.service.impl;

import club.yuit.auth.entity.MenusAuthority;
import club.yuit.auth.jpa.MenusAuthorityJpa;
import club.yuit.auth.service.MenuAuthorityService;
import club.yuit.common.exception.ArgumentsFailureException;
import club.yuit.common.response.BaseResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static club.yuit.common.response.HttpResponse.successResponse;


/**
 * @author yuit
 * @create 2018/11/23 14:51
 * @description
 * @modify
 */
@Service
public class MenuAuthorityServiceImpl implements MenuAuthorityService {

    private MenusAuthorityJpa menusAuthorityJpa;

    public MenuAuthorityServiceImpl(MenusAuthorityJpa menusAuthorityJpa) {
        this.menusAuthorityJpa = menusAuthorityJpa;
    }

    @Override
    @Transactional
    public BaseResponse saveList(String authId, List<String> menusIds)throws Exception {

        if(menusIds.size()<1){
            throw new ArgumentsFailureException();
        }


        this.menusAuthorityJpa.deleteByAuthorityId(authId);


        List<MenusAuthority> authorities = new ArrayList<>();

        menusIds.forEach(item->{
            MenusAuthority menusAuthority = new MenusAuthority();
            menusAuthority.setAuthorityId(authId);
            menusAuthority.setMenuId(item);
            authorities.add(menusAuthority);
        });

        this.menusAuthorityJpa.saveAll(authorities);


        return successResponse("提交成功");
    }
}
