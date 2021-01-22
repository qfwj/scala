package javatest.jpatest;

import javax.persistence.*;
import java.util.List;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/5/15 16:18
 */
@Entity
@Table(name ="T_test")
public class Teacher {
    @Id
    Long id;
    @Column(name = "name")
    String name;

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY) //必须要有mapBy 否则需要创建中间映射表
            List<Student> student;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
