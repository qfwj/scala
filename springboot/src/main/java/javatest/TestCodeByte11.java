package javatest;

public class TestCodeByte11 {
    private void mm(){
        System.out.println("mm");
    }
    void asas(){
        mm();
    }
    public static String test="1212";
    public static void main(String[] args) {
        new so().asas();

    }

    public static void main1(String[] args) {
    }
}

class pa {
    private void mm(){
        System.out.println("mm");
    }
    void asas(){
        mm();
    }
}

class so extends  pa{
     void mm(){
        System.out.println("12");
    }

}