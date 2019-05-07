package club.yuit.auth.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 32)
    private String id;
    @NotNull
    private String text;
    private String icon;
    private String parentId;

    private String link;
    private Date createTime;
    /**
     *  排序
      */
    private Integer sort;
    @Column(name = "[group]")
    private boolean group;
    @UpdateTimestamp
    private Date modifyTime;
    private String description;
    /**
     * 排除不做映射
     */
    @Transient
    private List<Menu> children=new ArrayList<>();

}
