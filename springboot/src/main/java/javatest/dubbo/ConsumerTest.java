package javatest.dubbo;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component
public class ConsumerTest {
   @Reference(check = false,version = "1.0.0")
    DubboTest dubboTest;
    public String ff(){
        return  dubboTest.hello("sdsdsdsd");
    }
}
