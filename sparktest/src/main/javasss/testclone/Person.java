package javasss.testclone;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/5/2910:00
 */
public class Person implements Cloneable {

    int age =12;
    String name = "hh";
     Professor professor;



    public Person() {
        System.out.println("nnnnn");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person newStudent = (Person) super.clone(); //深层次复制
        newStudent.professor = (Professor) professor.clone(); //深层次复制
        return newStudent;
//        return super.clone();  //浅复制
    }
}
