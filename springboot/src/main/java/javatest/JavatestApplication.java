package javatest;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ServletComponentScan
@RestController
@EnableDubbo
public class JavatestApplication {

    public static void main(String[] args)  throws Exception {

        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"C:\\Users\\nefu_\\Desktop\\test");
        SpringApplication.run(JavatestApplication.class, args);


    }

    @PostMapping("/test")
    public String dsd(@RequestBody String dsd){
        return "df";
    }

}
