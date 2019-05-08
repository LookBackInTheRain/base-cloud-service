package club.yuit.gateway.support;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author yuit
 * @date 2019/5/8 15:09
 **/
@Component
public class RemoteTokenService {

    private RestTemplate restTemplate;

    public RemoteTokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void loadAuthentication(String accessToken){




    }

}
