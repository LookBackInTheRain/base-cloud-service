package club.yuit.auth.entity.input.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author liulu
 * @create Time 2018/8/31 17:42
 * @description 只能修改菜单基本信息
 * @modify by
 * @modify time
 **/
@Data
public class ModifyMenusInput {
    @NotNull
    private String id;
    private String text;
    private String icon;
    private String link;
    private Integer sort;
    private String parentId;
    private Date modifyTime;
    private String description;

}
