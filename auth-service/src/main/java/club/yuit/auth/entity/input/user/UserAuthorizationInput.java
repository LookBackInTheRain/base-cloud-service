package club.yuit.auth.entity.input.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yuit
 * @create time 2018/9/12
 * @description
 * @modify by
 * @modify time
 **/
@Data
public class UserAuthorizationInput {

    @NotNull
    private String userId;
    @NotNull
    private List<String> auths;

}
