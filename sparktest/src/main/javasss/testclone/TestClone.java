package javasss.testclone;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/5/2910:00
 */
public class TestClone {
    public static void main(String[] args) throws Exception {
        Person pp = new Person();
        pp.professor = new Professor().setName("jjj");

        Object ddd = pp.clone();
        System.out.println("");
    }
}
