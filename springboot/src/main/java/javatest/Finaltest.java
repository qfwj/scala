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

       System.out.println(ii);
    }


   public  synchronized   void main1() {
        
    }
}