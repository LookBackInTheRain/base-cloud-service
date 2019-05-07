package club.yuit.auth.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author yuit
 * @date 2018/11/5 18:01
 */
public class BootOAuthExceptionJacksonSerializer extends StdSerializer<BootOAuth2Exception> {


    protected BootOAuthExceptionJacksonSerializer() {
        super(BootOAuth2Exception.class);
    }

    @Override
    public void serialize(BootOAuth2Exception value, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeObjectField("status", value.getHttpErrorCode());

        String msg = value.getMessage();

        if (msg != null) {
            msg = HtmlUtils.htmlEscape(msg);


            switch (msg) {
                case ExceptionCode.USER_EXPIRED:
                    msg = "账户已过期";
                    break;
                case ExceptionCode.USER_INFO_ERROR:
                    msg = "用户名或密码错误";
                    break;
                case ExceptionCode.USER_LOCKED:
                    msg = "账户已锁定，不可用";
                    break;
                case ExceptionCode.USER_NO_ENABLE:
                    msg = "账户不可用";
                    break;
                case ExceptionCode.TOKEN_EXPIRED:
                    msg="账号过期";
                default:
                    msg = "未知错误";
                    break;
            }

        }


        jgen.writeStringField("msg", msg);
        if (value.getAdditionalInformation() != null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jgen.writeStringField(key, add);
            }
        }
        jgen.writeEndObject();
    }
}

class ExceptionCode {

    /**
     * 账号过期
     */
    static final String USER_EXPIRED = "User account has expired";

    /**
     * token 过期
     */
    static final String TOKEN_EXPIRED = "Token has expired";
    /**
     * 不可用
     */
    static final String USER_NO_ENABLE = "User is disabled";
    /**
     * 密码或用户名错误
     */
    static final String USER_INFO_ERROR = "Bad credentials";
    /**
     * 锁定，不可用
     */
    static final String USER_LOCKED = "User account is locked";

}
