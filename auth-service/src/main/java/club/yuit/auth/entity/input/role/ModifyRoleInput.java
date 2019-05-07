package club.yuit.auth.entity.input.role;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yuit
 * @create time 2018/8/31
 * @description
 * @modify by
 * @modify time
 **/
@Data
public class ModifyRoleInput {

    @NotNull
    private String id;
    private String name;
    private String description;

}
