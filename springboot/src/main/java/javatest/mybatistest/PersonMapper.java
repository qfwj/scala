package javatest.mybatistest;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/6/17 14:10
 */
@Mapper
public interface PersonMapper {
    @Select("SELECT * FROM person ")
    Person findByState();
}
