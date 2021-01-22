package javatest.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/5/21 15:27
 */
@RestController
public class TestAspetController {

    @Autowired
    TestAspectService service;
    @RequestMapping("/testaspect")
    public List dsd(String sds) {
        List ll = new ArrayList();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        return ll;
    }


    @RequestMapping("/testaspect1")
    public String mm() {
        return "dfsdfdfsdfsdfdsfd";
    }
}
