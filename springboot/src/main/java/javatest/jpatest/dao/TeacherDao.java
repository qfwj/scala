package javatest.jpatest.dao;

import javatest.jpatest.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/5/15 16:18
 */
public interface TeacherDao extends JpaRepository<Teacher, Long> {
   Teacher findTeacherByName(String name);
}
