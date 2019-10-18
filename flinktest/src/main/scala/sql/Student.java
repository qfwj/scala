package sql;

import scala.Serializable;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/10/18 11:03
 */
public class Student implements Serializable {

       public String name;
        public Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Student(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public Student() {

        }

}
