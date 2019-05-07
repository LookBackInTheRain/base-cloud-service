package club.yuit.auth.entity.input.role;

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
public class RoleAuthorizationInput {

    @NotNull
    private String roleId;
    @NotNull
    private List<String> auths;

}
