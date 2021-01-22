package javatest.dubbo;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

//@Service(version = "1.0.0")
public class DubboTestImpl implements DubboTest{
    @Override
    public String hello(String args) {
        return args;
    }
}
