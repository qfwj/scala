package javatest.spi;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/10/30 21:46
 */
public class Dog  implements IShout {
    public Dog(){
        System.out.println("dog");
    }
    @Override
    public void shout() {
        System.out.println("wang wang");
    }
}