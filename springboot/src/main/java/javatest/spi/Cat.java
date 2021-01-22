package javatest.spi;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/10/30 21:46
 */
public class Cat implements IShout {


    public Cat(){
        System.out.println("cat");
    }
    @Override
    public void shout() {
        System.out.println("miao miao");
    }
}