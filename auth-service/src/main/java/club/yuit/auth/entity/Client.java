package club.yuit.auth.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author yuit
 * @date  2018/10/16  9:23
 *
 **/
@Data
@Entity
@Table(name = "clients")
public class Client  {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid")
    private String id;

    private String clientId;

    private String resourceIds;

    private Boolean isSecretRequired;

    private String clientSecret;

    private Boolean isScoped;

    private String scope;

    private String authorizedGrantTypes;

    private String registeredRedirectUri;

    private String authorities;
    private Boolean isAutoApprove;
    @Column(name = "accessTokenValiditySeconds")
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;

}
