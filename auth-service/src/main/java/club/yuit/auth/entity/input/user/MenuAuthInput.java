package club.yuit.auth.entity.input.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author liulu
 * @create Time 2018/8/31 16:21
 * @description 添加菜单的时候并分配权限
 * @modify by
 * @modify time
 **/
@Data
public class MenuAuthInput  {
    @NotNull
    private String text;
    private String icon;
    private String parentId;
    private String link;

    /**
     *  排序
     */
    @NotNull
    private Integer sort;

    private String description;
}
