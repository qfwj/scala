package com.qf;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;



@Configuration
@ApplicationPath("/jersey")
public class TestConfig  extends ResourceConfig {
    public TestConfig() {
        // 在这里注册相关Controller或者需要的特性支持
        register(TestController.class);
    }
}
