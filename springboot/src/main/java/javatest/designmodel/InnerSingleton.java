package javatest.designmodel;

/**
 * description:  <br>
 * date: 2021/1/19 15:10 <br>
 * author: zhbo <br>
 */
public class InnerSingleton {

    public static void main(String[] args) {
        System.out.println(33);
        System.out.println(singleton());
    }
    public static Object singleton() {
        return  InnerClass.singleton;
    }

    private static class InnerClass {
        private static Object singleton = new Object();
        static {
            System.out.println(12);
        }
        private Object singleton1 = new Object();
    }
}
