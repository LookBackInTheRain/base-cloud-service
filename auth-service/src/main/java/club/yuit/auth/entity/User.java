package club.yuit.auth.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author yuit
 * @date 2018/10/9  15:43
 *
 **/
@Data
@Table(name = "sys_user")
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid")
    private String id;
    private String username;
    private String email;
    private Boolean isEnable;
    private Boolean isExpired=false;
    private Boolean isLocked=false;
    private String password;
    private Integer gender;
    private String fullName;
    private  Integer age;
    private String description;
    @CreationTimestamp
    private Timestamp createTime;
    @UpdateTimestamp
    private Timestamp modifyTime;


    @Transient
    private List<? extends GrantedAuthority> authorities;


    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {

        if(this.isExpired!=null){
            return !this.isExpired;
        }else {
            return false;
        }

    }

    @Override
    public boolean isAccountNonLocked() {

       if(this.isLocked!=null){
           return !this.isLocked;
       }else {
           return false;
       }

    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return this.isEnable;
    }



    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
