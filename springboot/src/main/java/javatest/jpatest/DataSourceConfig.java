package javatest.jpatest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/5/15 16:18
 */
public class DataSourceConfig {

/*    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.operate")
    DataSource operate(){
        return DataSourceBuilder.create().build();
    }*/

}
