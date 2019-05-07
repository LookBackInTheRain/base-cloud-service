package club.yuit.auth.entity.input.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yuit
 * @create Time 2018/8/13
 * @description
 * @modify by
 * @modify time
 **/
@Data
public class ModifyUserInput {

    @NotNull
    private String id;
    private String fullName;
    private String username;
    private Integer age;
    private Integer gender;
    private String description;
    private String email;

}
