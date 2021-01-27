package javatest.jpatest;

import javatest.jpatest.dao.StudentDao;
import javatest.jpatest.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/5/18 9:44
 */
@RestController
public class JpaController {
    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;
    @PostMapping("testjpa/t")
    public String testJpa(@RequestBody Teacher teacher){
        /*Teacher en = teacherDao.findTeacherByName(teacher.getName());
        Teacher en1 =  teacherDao.findTeacherByName(teacher.getName());*/
        return teacher.getName();
    }

    @PostMapping("testjpa/s")
    public String testJpa2(@RequestBody Student student){
       Student ss = studentDao.findStudentByName(student.getName());
        return ss.getName();
    }
}
