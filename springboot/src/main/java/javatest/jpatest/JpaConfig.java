package javatest.jpatest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/5/15 16:17
 */
@Configuration
@EnableJpaRepositories(basePackages = "qf.javatest.jpatest.dao"
      /*  entityManagerFactoryRef = "operateManager"*/)
//@EntityScan("cn.people.idata.contentpool.entity")

public class JpaConfig {

    //@Autowired
   // @Qualifier(value = "operate")
   // DataSource dataSource;


 /*   @Bean
    LocalContainerEntityManagerFactoryBean operateManager(EntityManagerFactoryBuilder builder){

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("qf.javatest.jpatest");
        factory.setDataSource(dataSource);
        return factory;


    }*/

/*    @Bean
    PlatformTransactionManager platformTransactionManagerTwo(EntityManagerFactoryBuilder builder){
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = localContainerEntityManagerFactoryBeanTwo(builder);
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }*/

}
