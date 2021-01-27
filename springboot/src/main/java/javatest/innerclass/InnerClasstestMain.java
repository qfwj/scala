package javatest.innerclass;


import java.math.BigDecimal;

public class InnerClasstestMain {

    public static void main(String[] args) {
        BigDecimal dd = new BigDecimal("0.2");
        BigDecimal dd1 = new BigDecimal("0.1");
        System.out.println(dd.add( dd1));
        System.out.println(0.2 * 0.1);
        System.out.println(0.3 / 0.1);

        String sss = "ss"+"ss";
        sss.intern();
        String mm = "ssss";
        System.out.println(sss == mm);


        String s33 = new String("11");
        s33.intern();
        String s43 = "11";
        System.out.println(s33 == s43); //6返回false  7 返回true


        String s1 = "abc";
        final String s2 = "a";
        final String s3 = "bc";
        String s4 = s2 + s3;
        System.out.println(s1 == s4); // 带final返回true
        
        
    }
}
