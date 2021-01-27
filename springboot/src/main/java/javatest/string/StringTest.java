package javatest.string;

import java.util.Collections;

/**
 * description:  https://tech.meituan.com/2014/03/06/in-depth-understanding-string-intern.html
 * date: 2021/1/18 16:41 <br>
 * author: zhbo <br>
 */
public class StringTest {

    public static void main(String[] args) {
        String s3 = new String("1") + new String("1");
        s3.intern(); //将常量池 11 指向 s3
        String s4 = "11"; //常量池已经有了 直接使用
        System.out.println(s4 == s3); //true


        String s30 = new String("1") + new String("11");
        String s40 = "111"; //常量池已经有了 直接使用
        s30.intern(); //将常量池 11 指向 s3
        System.out.println(s40 == s30); //false

        String s31 = new String("121"); //先创建的常量池对象 再创建s31对象
        s31.intern();
        String s41 = "121";
        System.out.println(s41 == s31); //false
    }
}
