package club.yuit;

import club.yuit.common.response.BaseResponse;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yuit
 * @date 2019/5/15 16:02
 **/
@SpringBootApplication
@MapperScan("club.yuit.base.mapper")
public class BaseApplication {

    public static void main(String[] args){
        SpringApplication.run(BaseApplication.class,args);
    }

}
