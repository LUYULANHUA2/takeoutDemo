package com.lanhua.mapper;


import com.github.pagehelper.Page;
import com.lanhua.annotation.AutoFill;
import com.lanhua.dto.EmployeePageQueryDTO;
import com.lanhua.entity.Employee;
import com.lanhua.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("select *from employee")
    List<Employee> list();

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
//    @AutoFill(value = OperationType.INSERT)
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);


    /**
     * @param employee
     */
    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into employee(name, username, password, phone, sex, id_number, " +
            "status, create_time, update_time, create_user, update_user) VALUES(#{name},#{username},#{password},#{phone}" +
            ",#{sex},#{idNumber},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void insert(Employee employee);

    /**
     * @return
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    @AutoFill(value = OperationType.INSERT)
    void update(Employee employee);

    @Select("select *from employee where id = #{id}")
    Employee getById(Long id);
}
