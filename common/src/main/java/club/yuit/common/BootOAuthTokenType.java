package club.yuit.common;

/**
 * @author yuit
 * @date 2019/5/9 15:18
 **/
public enum BootOAuthTokenType {

    BEARER("bearer"),
    BASIC("basic");

    public String value;

    BootOAuthTokenType(String type){
        this.value=type;
    }

}
