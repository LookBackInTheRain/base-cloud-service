package club.yuit.auth.jpa;


import club.yuit.auth.entity.Client;

/**
 * @author yuit
 * @date 2019/4/24 15:38
 */
public interface ClientJpa extends RootJpa<Client,String> {

    Client findClientByClientId(String clientId);

}
