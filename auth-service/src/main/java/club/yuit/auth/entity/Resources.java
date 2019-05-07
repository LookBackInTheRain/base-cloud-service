package club.yuit.auth.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "resources")
public class Resources {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid",strategy = "uuid")
  private String id;
  private String name;
  private String url;
  private String description;
  private String method;
  @CreationTimestamp
  private Date createTime;
  @UpdateTimestamp
  private Date modifyTime;

}
