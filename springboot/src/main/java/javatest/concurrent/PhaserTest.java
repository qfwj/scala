package javatest.concurrent;

import java.util.concurrent.Phaser;

/**
 * description:
 * 测试Phaser
 +++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * <p>
 * date: 2021/1/20 11:02
 * author: zhbo
 */
public class PhaserTest {
    public static void main(String[] args) {


        Phaser phaser = new Phaser();

        for (int i = 0; i < 3; i++) {

            new Thread(new TreeStage(phaser, "name" + i)).start();
            phaser.register(); //可以动态添加需要执行的任务
        }
        System.out.println("sdfs");
        //   phaser.arriveAndAwaitAdvance(); //到达并等待其他任务到达
        //  phaser.arrive();  //到达之后不等待直接执行后续
        //phaser.arriveAndDeregister(); 到达之后清除自己，减小parties数量
        //  phaser.awaitAdvance(12); 等待阶段
        //phaser.getPhase();
    }
}


class TreeStage implements Runnable {

    Phaser phaser;
    String name;

    public TreeStage(Phaser phaser, String name) {
        this.phaser = phaser;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + "-stage1:" + Thread.currentThread());
        if (name.equals("name1")) {
            try {
                Thread.sleep(5000);
                System.out.println(name + "endd2323");
               // phaser.arriveAndDeregister();  //不用这个后面的arriveAndAwaitAdvance后将无法执行
                phaser.arrive(); //
            } catch (Exception e) {
            }
        } else {
            //  System.out.println(name + "-stage1: end" );
            phaser.arriveAndAwaitAdvance();


            System.out.println(name + "-stage2:" + Thread.currentThread());
            phaser.arriveAndAwaitAdvance();
            System.out.println(name + "-stage3:" + Thread.currentThread());
            // phaser.arriveAndAwaitAdvance();
        }
    }
}`