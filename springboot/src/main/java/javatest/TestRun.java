package javatest;


class MultiProcessorTask {

    private  boolean flag = true;

    public void runMethod() {
        while (flag) {
            new Simple(1);
        }
    }

    public void stopMethod() {
        System.out.println("change 'flag' field ...");
        flag = false;
    }
}


class ThreadA extends Thread {

    private MultiProcessorTask task;

    ThreadA(MultiProcessorTask task) {this.task = task;}

    @Override
    public void run() {
        task.runMethod();
    }
}

class Simple {
    private final int a;  // modify "a" as "final"

    Simple(int a) {
        this.a = a;
    }
}

public class TestRun {
    public static void main(String[] args) {
        MultiProcessorTask task = new MultiProcessorTask();
        ThreadA a = new ThreadA(task);
        a.start();
        task.stopMethod();
        System.out.println("it's over");
    }
}