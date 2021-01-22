package javatest;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/9/4 17:19
 */
public class VolatileTest {
    volatile int mm = 12;

    public static void main(String[] args) {
        new VolatileTest().main12();
 new VolatileTest().main123();
    }
    public  int main12() {
        mm =23;
        return mm;
    }

    public  void main123() {
        mm++;
    }
}