package javasss.testclone;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/5/2910:03
 */
public class Professor implements Cloneable{
    String name = "heeee";

    public Professor setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
