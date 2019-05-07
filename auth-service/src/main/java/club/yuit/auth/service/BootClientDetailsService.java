package club.yuit.auth.service;

import club.yuit.auth.entity.Client;
import club.yuit.auth.jpa.ClientJpa;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

/**
 * @author yuit
 * @date  2018/10/16  9:22
 *
 **/
@Component
public final class BootClientDetailsService implements ClientDetailsService {

    private ClientJpa clientJpa;

    public BootClientDetailsService(ClientJpa clientJpa) {
        this.clientJpa = clientJpa;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        Client client = this.clientJpa.findClientByClientId(clientId);

        if(client==null){
            throw new ClientRegistrationException("客户端不存在");
        }
        BootClientDetails details=new BootClientDetails(client);

        return details;
    }

}
