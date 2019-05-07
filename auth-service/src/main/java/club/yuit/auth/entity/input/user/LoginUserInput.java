package club.yuit.auth.entity.input.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yuit
 * @create time 2018/8/17
 * @description
 * @modify by
 * @modify time
 **/
@Data
public class LoginUserInput {

    @NotNull
    private String userNameOrEmail;
    @NotNull
    private String password;
}
