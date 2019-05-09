package club.yuit.common.support;

import lombok.Data;

import java.io.Serializable;

@Data
public final class PermissionProperties implements Serializable {
    private String url;
    private String token;

    public PermissionProperties(String url, String token) {
        this.url = url;
        this.token = token;
    }

    public PermissionProperties() {
    }
}
