package javatest.concurrent.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * description:  <br>
 * date: 2021/1/19 8:52 <br>
 * author: zhbo <br>
 */
public class StreamTest {

    static List<Integer> lll = new ArrayList<>();
    static {
        for(int i=0; i< 20;i++){
            lll.add(i);
        }
    }

    public static void testOps () {
        lll.stream().skip(2).forEach(f-> System.out.print(f+" ")); //跳过开头
        System.out.println();

        lll.stream().limit(2).forEach(f-> System.out.print(f+" ")); //只取头两条
        System.out.println();

        lll.stream().distinct().forEach(f-> System.out.print(f+" "));
        System.out.println();

        lll.stream().peek(f-> System.out.println(f)).forEach(f-> System.out.print(f+" ")); //peek跟map类似 只不过跟的是个consumer


    }

    public static void main(String[] args) {
        testOps();


        Function f1 = x-> {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }
            System.out.println(13);
            return 13;
        };
        Function f2 = x-> {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }
            System.out.println(13);
            return 13;
        };
        Function f3 = x-> {
            System.out.println(12);
            return 12;
        };
        List<Function> list = new ArrayList(){{
             add(f1);  add(f2);add(f3);
        }} ;
       Optional op =  list.parallelStream().map(f->f.apply(1)).skip(1).findAny();
        System.out.println(op.get());





        System.out.println( IntStream.range(2,10).distinct().max());
        List<Integer> ll = new ArrayList<>();
        ll.add(1);  ll.add(2);  ll.add(3); // ll.add(4);
        Spliterator spliterator1 =  ll.spliterator();

       // spliterator1.forEachRemaining(f-> System.out.println(f));
        Spliterator s2 = spliterator1.trySplit();

   //     s2.forEachRemaining(f-> System.out.println(f));
    //    spliterator1.forEachRemaining(f-> System.out.println(f));

        System.out.println(spliterator1.estimateSize());
        System.out.println(s2.estimateSize());

        for(int i=0; i< 20;i++){
            ll.add(i);
        }

        list.parallelStream().limit(2).forEach(f-> System.out.println(f));
        list.parallelStream().skip(2).forEach(f-> System.out.println(f));
      //  ll.stream().map(f->f++).forEach(f-> System.out.println(f));
        /**
         * 并行流基于ForkJoinTask来实现
         */
        ll.parallelStream().forEach(f-> System.out.println(Thread.currentThread() + ":"+f));

    }
}
