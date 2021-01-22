package javatest.future;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/1/8 16:56
 */
public class TestFuture {


    public static void testAfterEither() {
        CompletableFuture t1 = CompletableFuture.supplyAsync(() -> {
            try{
                Thread.sleep(2000);
            }catch ( Exception e){

            };
            System.out.println(Thread.currentThread() + "t1");
            return 12;
        });
        CompletableFuture.supplyAsync(() -> {
            try{
                Thread.sleep(8000);
            }catch ( Exception e){

            };
            System.out.println(Thread.currentThread() + "t2");
            return 12;
        }).runAfterEither(t1, ()->{
                    System.out.println(Thread.currentThread() + "AfterEither");
                    System.out.println(Thread.currentThread());
                }).join();
    }


    public static void testCombine() throws Exception {
        /*combine中循环等待问题*/
        CompletableFuture<Integer> t1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(111);
            } catch (Exception e) {
            }
            System.out.println(Thread.currentThread() + "1");
            return 12;
        });
        CompletableFuture<Integer> t2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
            }
            System.out.println(Thread.currentThread() + "2");
            return 12;
        });
        CompletableFuture<Integer> t3 = CompletableFuture.supplyAsync(() -> {
            try {
               // Thread.sleep(3333);
            } catch (Exception e) {
            }
            System.out.println(Thread.currentThread() + "3");
            return 12;
        });
        t1.thenCombineAsync(t2, (m,n) -> {
            System.out.println(Thread.currentThread() + "com1");
            return m+n;
        }).thenCombineAsync(t3,(m,n) -> {
            System.out.println(Thread.currentThread() + "com2");

            return m+n;
        } ).get();

        System.out.println("");
    }

    public static void testAllfo() throws Exception {
        List<CompletableFuture<Integer>> list = new ArrayList<>();
        list.add(   CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3333);
            } catch (Exception e) {
            }
            System.out.println(Thread.currentThread() + "3");
            return 12;
        }));
        list.add(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3333);
            } catch (Exception e) {
            }
            System.out.println(Thread.currentThread() + "3");
            return 12;
        }));
        CompletableFuture<Integer> f1 =  CompletableFuture.completedFuture(0);
        for(CompletableFuture<Integer> f : list) {
           f1 =  f1.thenCombine(f, ( t1,  t2)->  t1 + t2);
        }
        System.out.println(f1.get());
    }
    public static void main(String[] args) throws Exception {
        testCombine();
        /*使用静态方法进行创建*/
        CompletableFuture<Integer> future = CompletableFuture.completedFuture(12);
        //future.thenComposeAsync( f -> future.get());


        CompletableFuture future1 = future.thenApplyAsync((f) -> {
            try {
                Thread.sleep(20000);
            } catch (Exception e) {

            }
            System.out.println("future1 end");

            return f + 112;
        });

        CompletableFuture future2 = future
                .thenApplyAsync(f -> {
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                    String ss = new String(f + "asd");
                    System.out.println("future2 end");

                    return ss;
                });
        CompletableFuture future23 = future2.whenComplete((f, t) -> System.out.println(f + "111"));
        /*完成一个即可*/
        future23.runAfterEitherAsync(future1, () ->
                System.out.println("either")
        );

        /*必须都完成一个即可*/
        future23.runAfterBothAsync(future1, () ->
                System.out.println("both")
        );

        System.out.println("asasd");
        Thread.sleep(5000000);
        System.out.println(Thread.currentThread().getName() + "asasd");

    }
}
