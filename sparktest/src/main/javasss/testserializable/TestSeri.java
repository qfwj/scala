package javasss.testserializable;


import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/5/299:36
 */
public class TestSeri {
    private static String file_name = "C:\\Users\\nefu_\\Desktop\\obj.bin";
    public static void main(String[] args) {
//        try{
//            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file_name));
//            oos.writeObject(new Student());
//            System.out.println("序列化成功");
//            oos.close();
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }

        new Student();
        Object obj = null;
        try{

            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file_name));
             obj = input.readObject();
            input.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


}
