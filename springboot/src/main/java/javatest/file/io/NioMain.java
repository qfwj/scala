package javatest.file.io;

import java.io.File;

public class NioMain {
    public static void main(String[] args) {

        File ff = new File("hhhh");
         // ff.createNewFile()才会调用系统创建磁盘文件
        Thread ss = new Thread(() -> {
            synchronized (NioMain.class) {
                try {
                    for (int i = 1; ; i++) {
                        System.out.println("i");

                    }
                } catch (Exception e) {

                }

            }
        });
        Thread ss1 = new Thread(() -> {
            synchronized (NioMain.class) {
                System.out.println("dsd");
            }
        });
        ss1.setName("test111");
        ss.start();
        ss1.start();

    }
}
