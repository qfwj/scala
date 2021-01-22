
package javatest.sinal;

import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 通过Signal测试中断类型 钩子
 * @author: zhbo
 * @date 2020/1/9 14:29
 */
public class Singnal {

    public static void main(String[] args) throws Exception {

        /**
         * kill -9 :不回调hook
         * kill -15：回调hook
         *
         * testSignal重写了SignalHandler的handle方法，捕获到的kill信号也是最终在handle里处理。
         * 在main里注册了三个kill信号：TERM（kill -15）、USR1（kill -10）、USR2（kill -12）
         *
         * 补充1：
         * 在Linux下支持的信号（具体信号kill -l命令查看）：
         * SEGV, ILL, FPE, BUS, SYS, CPU, FSZ, ABRT, INT, TERM, HUP, USR1, USR2, QUIT, BREAK, TRAP, PIPE
         * 在Windows下支持的信号：
         * SEGV, ILL, FPE, ABRT, INT, TERM, BREAK
         *
         */

        Signal sig = new Signal(getOSSignalType());
        Signal.handle(sig, new SignalHandler() {
            @Override
            public void handle(Signal signal) {
                System.out.println(signal);
                Thread t = new Thread(() -> {
                    System.out.println("ShutdownHook execute start...");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("ShutdownHook execute end...");
                    } catch (Exception e) {
                    }
                }, "ShutdownHook-Thread");
               // Runtime.getRuntime().addShutdownHook(t);

             //   System.exit();
               Runtime.getRuntime().exit(-1);
            }
        });

        Thread t = new Thread(() -> {

            System.out.println("Shutd 1212...");
            try {
                System.out.println("ShutdownHook123 434...");
            } catch (Exception e) {
            }

        }, "ShutdownHook-Thread");
        Runtime.getRuntime().addShutdownHook(t);
        System.out.println("end");
            Thread.sleep(121212123);
    }

    public static String getOSSignalType() {
        String ss = System.getProperties().getProperty("os.name").
                toLowerCase().startsWith("win") ? "INT" : "USR2";
        return ss;
    }
}
