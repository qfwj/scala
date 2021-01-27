package javatest;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/6/3 10:06
 */
public class MethodHandlesTest {


    public String toString(String s) {

        return "hello," + s + "MethodHandle";
    }

    public static void main(String[] args) {
        MethodHandlesTest mhTest = new MethodHandlesTest();
        MethodHandle mh = getToStringMH();  //获取方法句


        try {
            // 1.调用方法：
            String result = (String) mh.invokeExact(mhTest, "ssssss");  //根据方法句柄调用方法----注意返回值必须强转
            System.out.println(result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


        // 2.or like this:
        try {
            MethodHandle methodHandle2 = mh.bindTo(mhTest);
            String toString2 = (String) methodHandle2.invokeWithArguments("sssss");
            System.out.println(toString2);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


        // 得到当前Class的不同表示方法，最后一个最好。一般我们在静态上下文用SLF4J得到logger用。
        System.out.println(MethodHandlesTest.class);
        System.out.println(mhTest.getClass());



    }


    /**
     * 获取方法句柄
     * @return
     */
    public static MethodHandle getToStringMH() {

        MethodType mt = MethodType.methodType(String.class, String.class);
        //获取方法类型 参数为:1.返回值类型,2方法中参数类型

        MethodHandle mh = null;
        try {
            mh = MethodHandles.lookup().findVirtual(MethodHandlesTest.class, "toString", mt);  //查找方法句柄
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return mh;}


}
