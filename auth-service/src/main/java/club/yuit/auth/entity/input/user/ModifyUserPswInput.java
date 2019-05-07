package club.yuit.auth.entity.input.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author liulu
 * @create Time 2019/1/10 14:41
 * @description
 * @modify by
 * @modify time
 **/
@Data
public class ModifyUserPswInput {
    @NotNull
    private String id;
    private String password;
    private String newPassword;
}
