package javatest;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/9/4 17:19
 */
public class Finaltest {
    {
        System.out.println(ii);
        ii = 24;
        System.out.println(ii);
    }
    static int ii =12;
   static Finaltest ff = new Finaltest();
    {
        System.out.println(ii);
            ii = 23;
        System.out.println(ii);
    }

   public static void main(String[] args) {

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
        int iii =12;
        try {

            System.out.println(12/i);
            return iii;

        } catch (Exception e) {
            iii =13;
        } finally {
            iii =13;
        }
        return i;
    }
}