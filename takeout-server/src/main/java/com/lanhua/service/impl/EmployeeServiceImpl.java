package com.lanhua.service.impl;

import com.lanhua.mapper.EmployeeMapper;
import com.lanhua.service.EmployeeService;
import com.lanhua.dto.EmployeeLoginDTO;
import com.lanhua.entity.Employee;
//import com.lanhua.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.lanhua.mapper.testMapper;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

//    @Autowired
//    private testMapper testMapper;
    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
//        String username = employeeLoginDTO.getUsername();
//        String password = employeeLoginDTO.getPassword();
//
//        //1、根据用户名查询数据库中的数据
//        Employee employee = employeeMapper.getByUsername(username);
//
//        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
//        if (employee == null) {
//            //账号不存在
//            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
//        }
//
//        //密码比对
//        // TODO 后期需要进行md5加密，然后再进行比对
//        if (!password.equals(employee.getPassword())) {
//            //密码错误
//            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
//        }
//
//        if (Objects.equals(employee.getStatus(), StatusConstant.DISABLE)) {
//            //账号被锁定
//            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
//        }
//
//        //3、返回实体对象
//        return employee;
        return null;
    }

    @Override
    public List<Employee> list() {
        return employeeMapper.list();
    }

    public List<Employee> employeeList(){
        return employeeMapper.list();
    }
}