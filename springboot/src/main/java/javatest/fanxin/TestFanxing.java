package javatest.fanxin;


import javatest.entity.Father;
import javatest.entity.GrandFather;
import javatest.entity.Son;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 *
 *
 * 测试java泛型
 * 协变
 * 逆变
 * 不变
 * PECS: produce extends consumer super
 *
 *
 * @author: zhbo
 * @date 2019/11/5 14:39
 */
public class TestFanxing {

    public static void main(String[] args) {
        List<? extends Father> ll = new ArrayList();
         GrandFather grandFather =  ll.get(0); // consumer super  否则编译会报错

        List<? super Father> ll1 = new ArrayList();
        ll1.add(new Son()); // consumer super  否则编译会报错
       // Son ss   =  ll1.get(0);

    }
}


