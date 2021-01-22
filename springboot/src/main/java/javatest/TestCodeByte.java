package javatest;

public class TestCodeByte {

    public static void main(String[] args) {
        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s4 == s3);


        String s31 = new String("121");
        s31.intern();
        String s41 = "121";
        System.out.println(s41 == s31);
    }


}
