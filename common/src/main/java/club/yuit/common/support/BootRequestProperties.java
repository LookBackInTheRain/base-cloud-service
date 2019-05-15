package club.yuit.common.support;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public final class BootRequestProperties implements Serializable {
    private String url;
    private String token;
    private String method;
    private String serverId;

    public BootRequestProperties(String url, String token, String method, String serverId) {
        this.url = url;
        this.token = token;
        this.method = method;
        this.serverId = serverId;
    }

    public BootRequestProperties(String url, String token, String method) {
        this.url = url;
        this.token = token;
        this.method = method;
    }

    public BootRequestProperties() {
    }
}
