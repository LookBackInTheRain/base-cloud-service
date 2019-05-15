package club.yuit.base.mapper;

import club.yuit.base.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuit
 * @date 2019/5/15 16:58
 **/
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

     List<Menu> findMenuByUsername(String username);

}
