package club.yuit.auth.service;


import club.yuit.auth.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author yuit
 * @date time 2018/10/11  9:13
 *
 **/
@Service
public final class BootUserDetailService implements UserDetailsService {


    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public BootUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= this.userService.login(username);

        if(user==null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        return user;
    }
}
