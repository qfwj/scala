package javatest.classload;

import java.lang.reflect.Method;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/10/30 17:55
 */
public class ClassLoadtestMain {
    public static void main(String[] args) throws Exception {
        //这个类class的路径
        String classPath = "C:\\Users\\擎风\\Desktop\\TestClassLoad1.class";
        ClassLoader myClassLoader = new MyClassLoader(classPath);
        //Class test= Class.forName("java.util.HashMap", true, myClassLoader);
        String classPath1 = "D:\\zhbo\\project\\java\\javatest\\src\\main\\java\\qf\\javatest\\classload\\TestClassLoad1.class";
        ClassLoader myClassLoader1 = new MyClassLoader1(classPath1);

        //类的全称
        String packageNamePath = "qf.javatest.classload.TestClassLoad1";

        //加载Log这个class文件

       Class<?> test111= Class.forName( "qf.javatest.classload.TestClassLoad1", true,myClassLoader1);
       Class<?> test = myClassLoader1.loadClass(packageNamePath);
        Object object222 = test.newInstance();

        Class<?> test2= myClassLoader1.loadClass(packageNamePath);


        System.out.println("类加载器是:" + test.getClassLoader());

        //利用反射获取main方法
        Method method = test.getDeclaredMethod("main");
        Object object = test.newInstance();
        method.invoke(object);

        Method method2 = test2.getDeclaredMethod("main");
        Object object2 = test2.newInstance();
        method2.invoke(object2);


        /*新的classloder*/
    //    MyClassLoader myClassLoader1 = new MyClassLoader(classPath);

        //加载Log这个class文件
        Class<?> test1 = myClassLoader1.loadClass(packageNamePath);

        System.out.println("类加载器是:" + test1.getClassLoader());

        //利用反射获取main方法
        Method method1 = test1.getDeclaredMethod("main");
        Object object1 = test1.newInstance();
        method1.invoke(object1);



        //加载Log这个class文件
     //   Class<?> test2 = myClassLoader.loadClass(packageNamePath);

     //   System.out.println("类加载器是:" + test2.getClassLoader());

        //利用反射获取main方法
        //Method method2= test2.getDeclaredMethod("main");
       // Object object2 = test2.newInstance();
       // method2.invoke(object2);
    }
}
