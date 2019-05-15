package club.yuit.base.service;

import club.yuit.base.entity.Menu;
import club.yuit.base.mapper.MenuMapper;
import club.yuit.common.response.SimpleResponse;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuit
 * @date 2019/5/15 17:07
 **/
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> {



    public SimpleResponse getUserMenus(String username){
        List<Menu>  menus = this.baseMapper.findMenuByUsername(username);
        return null;
    }

}
