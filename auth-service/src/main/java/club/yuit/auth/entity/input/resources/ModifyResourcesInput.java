package club.yuit.auth.entity.input.resources;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yuit
 * @create time 2018/9/6
 * @description
 * @modify by
 * @modify time
 **/
@Data
public class ModifyResourcesInput {

    @NotNull
    private String id;
    private String name;
    private String url;
    private String description;
    private String method;

}
