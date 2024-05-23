package com.lanhua.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lanhua.constant.MessageConstant;
import com.lanhua.constant.PasswordConstant;
import com.lanhua.constant.StatusConstant;
import com.lanhua.context.BaseContext;
import com.lanhua.dto.EmployeeDTO;
import com.lanhua.dto.EmployeePageQueryDTO;
import com.lanhua.exception.AccountLockedException;
import com.lanhua.exception.AccountNotFoundException;
import com.lanhua.exception.PasswordErrorException;
import com.lanhua.mapper.EmployeeMapper;
import com.lanhua.result.PageResult;
import com.lanhua.service.EmployeeService;
import com.lanhua.dto.EmployeeLoginDTO;
import com.lanhua.entity.Employee;
//import com.lanhua.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.lanhua.mapper.testMapper;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
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
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();
        password = DigestUtils.md5Hex(password);//md5对密码进行加密
//        password =  DigestUtils.md5D(password);//md5对密码进行加密
        log.info("输出密码为：{}", password);
        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

//        if (Objects.equals(password, employee.getPassword())) {
//            return employee;
//        } else throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
//        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (Objects.equals(employee.getStatus(), StatusConstant.DISABLE)) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }
        return employee;
        //3、返回实体对象

    }

    @Override
    public List<Employee> list() {
        return employeeMapper.list();
    }

    @Override
    public void add(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        BeanUtils.copyProperties(employeeDTO, employee);

        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        employee.setStatus(StatusConstant.ENABLE);
        employee.setPassword(DigestUtils.md5Hex(PasswordConstant.DEFAULT_PASSWORD));

        //TODO 后期注入当前的用户
        employee.setUpdateUser(BaseContext.getCurrentId());
        employee.setCreateUser(BaseContext.getCurrentId());

        log.info("添加的用户信息，{}", employee);

        employeeMapper.insert(employee);
    }

    @Override
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {

        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());

        log.info("查看传入的参数，{}", employeePageQueryDTO);
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);

        long total = page.getTotal();
        List<Employee> list = page.getResult();
        return new PageResult(total, list);
    }

    @Override
    public void starOrStop(Integer status, Long id) {

        Employee employee = Employee.builder().status(status).id(id).build();
        employeeMapper.update(employee);
    }

    @Override
    public Employee getById(Long id) {

        Employee employee = employeeMapper.getById(id);
        employee.setPassword("******");
        return employee;
    }

    public List<Employee> employeeList() {
        return employeeMapper.list();
    }
}
