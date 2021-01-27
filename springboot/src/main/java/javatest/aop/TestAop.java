package javatest.aop;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/11/12 14:56
 */

@Component
public class TestAop {

    @PostConstruct
    public void te(){
        System.out.println("postconstruct");
    }

    public  void testAop() {
        System.out.println("aop" + 1/0);
    }
}
