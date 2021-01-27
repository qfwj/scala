package javatest.jpatest.dao;

import javatest.jpatest.Student;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/5/15 16:18
 */
public interface StudentDao extends JpaRepository<Student, Long> {
   Student findStudentByName(String name);
}
