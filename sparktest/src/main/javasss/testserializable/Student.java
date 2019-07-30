package javasss.testserializable;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/5/299:36
 */
public class Student implements Serializable {

    String name12 = "1212";
    /*反序列化会进行类的加载*/
    static {
        System.out.println("yayya");
    }


    public Student(String name) {
        this.name12 = name;
        System.out.println("ffff");

    }

    /*反序列化不会调用构造函数*/
    public Student() {
        System.out.println("dssds");
    }
    /*
    * 判断反序列化会不会失败  没这个字段就会跟就class文件字节码生成 一旦序列化前 跟序列化之后 不一致就会反序列化失败
    * */
    private static final long serialVersionUID = 55799L;


}
