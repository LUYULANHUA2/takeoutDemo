package com.lanhua.mapper;


import com.lanhua.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("select *from employee")
    List<Employee> list();
}
