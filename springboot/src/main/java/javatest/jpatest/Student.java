package javatest.jpatest;

import javax.persistence.*;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/5/15 16:18
 */
@Entity
@Table(name ="S_test")
public class Student {

        @Id
        Long id;
        @Column(name = "name")
        String name;

    @Column(name = "t_id") //关联teacher表
            String tId;

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="t_id" , insertable = false, updatable = false)
    Teacher teacher; //必须要有这字段

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
