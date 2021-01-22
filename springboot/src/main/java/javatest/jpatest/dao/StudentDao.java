package javatest.jpatest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import qf.javatest.jpatest.Student;
import qf.javatest.jpatest.Teacher;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/5/15 16:18
 */
public interface StudentDao extends JpaRepository<Student, Long> {
   Student findStudentByName(String name);
}
