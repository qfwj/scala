package javatest.number;

import java.math.BigDecimal;

/**
 * description:  <br>
 * date: 2021/1/12 18:03 <br>
 * author: zhbo <br>
 */
public class NumTest {
    public static void main(String[] args) {
        BigDecimal dd = new BigDecimal("0.2");
        BigDecimal dd1 = new BigDecimal("0.1");
        System.out.println(dd.add( dd1));
//        0.3
//0.30000000000000004
//0.020000000000000004
//2.9999999999999996
        System.out.println(0.2 + 0.1);
        System.out.println(0.2 * 0.1);
        System.out.println(0.3 / 0.1);
    }
}
