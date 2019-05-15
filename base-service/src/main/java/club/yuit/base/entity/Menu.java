package club.yuit.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author yuit
 * @date 2019/5/15 16:48
 **/
@TableName("menus")
@Setter
@Getter
public class Menu {

    @TableId(type = IdType.UUID)
    private String id;
    private String text;
    private String icon;
    private String parentId;
    private String link;
    private Date createTime;
    /**
     *  排序
     */
    private Integer sort;

    private boolean group;
    private Date modifyTime;
    private String description;

}
