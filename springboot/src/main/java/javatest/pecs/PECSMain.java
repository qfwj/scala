package javatest.pecs;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 测试逆变 协变  型变
 * java 泛型不支持型变 使用通配符来支持协变
 * https://www.jianshu.com/p/90948ff4a940?utm_source=oschina-app
 * 最大的特点： 关于? extend T 还是 ? super T
 * 在类型实参（也就是在方法的参数栏中或者方法体内部）
 * 一个类的
 * 如果想调用是带有返回类型实参的  声明时就用 super 比如：List<? extend Fruit> list ;  list.get(0)
 * 如果想调用其方法参数中带有 类型实参   声明时就用 extend   List<? super Fruit> list;  list.add()
 *
 * 另一种解释：
 *  实际类型：？ extend  T 为  T某一种子类，肯定为T的子类  那么就可以拿到返回值 用T来上溯造型
 *  实际类型：？ super  T 为  T某一种父类， 而T的任何子类 就是(肯定是) T的某一种父类， 传入参数时 就传入T的任意子类即可
 * */
public class PECSMain<T> {

    public static <T>  T show(T t){
        return t;
    }

    //方法二
    public static List<? super Orange  > set(List<? extends Orange  > list, List<? super Orange  > list1 )
    {    Orange dd =list.get(0);
        list1.add(list.get(0));
        return  list1;
    }


    public static void main(String[] args) {


        List<?  extends     Fruit> list1 = new ArrayList<Apple>();  //协变
        List<?  super      Apple> list2 = new ArrayList<Fruit>(); //逆变



        Source<Integer> df = new Source<Integer>();
        Source<Integer> df1 = show(df);
        int i = 0;

        assert i == 1;

        List< Fruit> fruits = new ArrayList<Fruit>(); // 逆变



        List<? super Orange> oranges  = fruits;

        Object ss2 = oranges.get(0);
        oranges.add(new Orange1());
        oranges.add(new Orange());
        // <R> Stream<R> map(Function<? super T, ? extends R> mapper);

        List<Orange1 > orange1s = new ArrayList<>();

        List<? extends Fruit> fdf = new ArrayList<Orange>(); //协变

        List<?  super    Orange> list = new ArrayList<Fruit>();
        list.add(new Orange());
        ArrayList<? extends Fruit> mm = new ArrayList<Apple>();
        ArrayList<? super Apple> mm1 = new ArrayList<Fruit>();
        Fruit dd = mm.get(12);


        Fruit dd1 = fdf.get(0);



        Object[] dd2 = new String[2];
        dd2[1] = new Object();
       ;
        list.add(new Orange()); //消费 CS



        Fruit ff = list1.get(0); //生产 PE
        set(orange1s, fruits).addAll(orange1s);
        Collections.copy(fruits, orange1s); //

        new FuncTest<>().extendsTest(new Orange());
        //FuncTest1<Orange> fff = new FuncTest1<Orange>();
       // fff.consumer(new Source(new Fruit()));


        new FuncTest1<Fruit, Fruit>().consumer((Fruit fruit) -> new Orange());

    }
}

class FuncTest1<T,R>{
    T t;
    R  consumer(Function<? super T, ? extends R> tt) {
       return tt.apply(t);
    }


}


class FuncTest<T>{
    public <R extends Fruit> T test(Class<T> t) throws InstantiationException,
            IllegalAccessException {
        return t.newInstance();

    }
    public <E extends Fruit> void extendsTest(E list){
        System.out.println("extendsTest ok");
    }

    public  void superTest(List<? super T> list){
        System.out.println("superTest ok");
    }


}


class Source<T> {
    T t;
    Source(T t){
        this.t= t;
    }
    Source(){

    }
     T get(){
        return t;
    }
}

class Fruit {

    public void fruit(Object obj) {
    }
}


class Orange extends Fruit{
    public void orange(Object obj) {
    }
}

class Orange1 extends Orange{
    public void orange1(Object obj) {
    }
}

class Apple extends  Fruit {
    public void apple(Object obj) {
    }
}