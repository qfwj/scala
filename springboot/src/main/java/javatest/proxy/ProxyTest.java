package javatest.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
 *
 * @Description: TODO
 * @author: zhbo
 * @date 2020/4/2 9:43
 */
public class ProxyTest {
    private static final char HexCharArr[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    private static final String HexStr = "0123456789abcdef";

    public static String byteArrToHex(byte[] btArr) {
        char strArr[] = new char[btArr.length * 2];
        int i = 0;
        for (byte bt : btArr) {
            strArr[i++] = HexCharArr[bt>>>4 & 0xf];
            strArr[i++] = HexCharArr[bt & 0xf];
        }
        return new String(strArr);
    }


    public static void main(String[] args) throws Exception {

        /*long start = System.currentTimeMillis();
        for (int i=0; i<1000000; i++)
        {
            TestA a1 = new TestA();
        }
        System.out.println("使用构造函数创建耗时：" + (System.currentTimeMillis() - start));*/
        //   Class.forName("qf.javatest.proxy.TestA").newInstance();

/*

        long start = System.currentTimeMillis();
        Class gg = Class.forName("qf.javatest.proxy.TestA");

        for (int i = 0; i < 16; i++) {
            gg.newInstance();

        }
        System.out.println("使用：" + (System.currentTimeMillis() - start));
        long start1 = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            gg.newInstance();

        }
        System.out.println("使用构造函数创建耗时：" + (System.currentTimeMillis() - start1));
*/





        TestA ss = new TestA();
         ProxyA nn =new ProxyA(ss);


        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");


        //两种写法
        //  A a = (A)Proxy.newProxyInstance(A.class.getClassLoader(),TestA.class.getInterfaces(), nn);
        A a = (A) Proxy.newProxyInstance(A.class.getClassLoader(),new Class[] {A.class}, nn);
    //    A a2 = (A)Proxy.newProxyInstance(A.class.getClassLoader(),new Class[] {A.class}, nn);
        for(int i =0; i< 20; ++ i){
            a.say();
        }

        System.out.println("");

    }


}


class ProxyA implements InvocationHandler {
    private Object target;

    public ProxyA(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            System.out.println(method.getName());
            System.out.println("1");
            method.invoke(target, args);
            System.out.println("3");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("");
        }
        return null;
    }

}


class TestA implements A {
    @Override
    public void say() {
        System.out.println("2");
    }

    @Override
    public void eat() {

    }
}

interface A {
    void say();

    void eat();
}