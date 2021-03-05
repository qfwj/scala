package javatest.method;

public class MethodTest {

    public static void main(String[] args) {
        new MethodSon().test2();
    }
}


class MethodParent {
    private void test1() {
        System.out.println("p  1");
    }

    void test2() {
        test1(); //私有方法使用invokeSpecial
    }
}

class MethodSon extends MethodParent {
    private void test1() {
        System.out.println("s  1");
    }
}