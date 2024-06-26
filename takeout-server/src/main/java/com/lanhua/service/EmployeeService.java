package com.lanhua.service;

import com.lanhua.dto.EmployeeDTO;
import com.lanhua.dto.EmployeeLoginDTO;
import com.lanhua.dto.EmployeePageQueryDTO;
import com.lanhua.dto.PasswordEditDTO;
import com.lanhua.entity.Employee;
import com.lanhua.result.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    List<Employee> list();

    void add(EmployeeDTO employeeDTO);

    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void starOrStop(Integer status, Long id);

    Employee getById(Long id);


    void update(EmployeeDTO employeeDTO);

    void editPassword(PasswordEditDTO passwordEditDTO);
}
