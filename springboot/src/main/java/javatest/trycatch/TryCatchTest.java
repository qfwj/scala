package javatest.trycatch;

import java.util.function.Function;

/**
 * description:
 * date: 2021/2/23 10:47
 * author: zhbo
 */
public class TryCatchTest {


    public static void main(String[] args) {

        Function d = i->{
           return 121;
        };

        Integer i = 128;
        Integer j = 128;
        System.out.println(i==j);
        Object o1 =  main1(1);
        Object o2 =  main2(1);
        System.out.println("sf");
    }


    public static Object    main1(int i) {
        try {
            System.out.println(12/i);
            return new Integer(10);

        } catch (Exception e) {
            return new Integer(11);

        } finally {
            return new Integer(12);
        }

    }


    public static Object    main2(int i) {
        int ii =12;
        try {
            System.out.println(12/i);
            return ii;

        } catch (Exception e) {
            ii =13;
        } finally {
            ii =13;
        }
        return i;
    }
}
