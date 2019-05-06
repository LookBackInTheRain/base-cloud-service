package club.yuit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yuit
 * @date 2019/4/8 17:09
 */
@SpringBootApplication
@EnableSwagger2
public class AuthServiceApplication {

    public static void main(String[] args){
        SpringApplication.run(AuthServiceApplication.class,args);
    }

}
