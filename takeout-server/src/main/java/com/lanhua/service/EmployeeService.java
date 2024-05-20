package com.lanhua.service;

import com.lanhua.dto.EmployeeLoginDTO;
import com.lanhua.entity.Employee;
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
}
