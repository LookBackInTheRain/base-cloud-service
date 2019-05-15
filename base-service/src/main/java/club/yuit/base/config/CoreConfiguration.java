package club.yuit.base.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author yuit
 * @date 2019/5/15 16:31
 **/
@Configuration
public class CoreConfiguration {


    @Bean
    public DataSourceTransactionManager transactionManager(HikariDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}
