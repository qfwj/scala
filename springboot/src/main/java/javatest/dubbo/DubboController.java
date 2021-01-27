package javatest.dubbo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DubboController {
   @Autowired
    ConsumerTest consumerTest;

    @RequestMapping("/dubbotest")
    public String fsd(){
        return  consumerTest.ff();
    }
}
