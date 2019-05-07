package club.yuit.auth.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author yuit
 * @create Time 2018/8/8
 * @description
 * @modify by
 * @modify time
 **/

@Data
@Entity
@Table(name = "role")
public class Role {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid",strategy = "uuid")
  private String id;
  @NotNull
  private String name;
  @UpdateTimestamp
  private Date createTime;
  @UpdateTimestamp
  private Date modifyTime;
  private String description;

}
