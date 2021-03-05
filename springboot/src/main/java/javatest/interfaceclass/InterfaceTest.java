package javatest.interfaceclass;

public class InterfaceTest implements  Interface1, Interface2{

    public static void main(String[] args) {
        //多个接口 同名的字段 编译无法通过
        InterfaceTest interfaceTest =  new InterfaceTest();

    }
}


