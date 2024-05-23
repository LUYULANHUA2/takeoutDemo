package com.lanhua.controller.admin;

import com.lanhua.constant.JwtClaimsConstant;
import com.lanhua.dto.EmployeeDTO;
import com.lanhua.dto.EmployeeLoginDTO;
import com.lanhua.dto.EmployeePageQueryDTO;
import com.lanhua.entity.Employee;
import com.lanhua.properties.JwtProperties;
import com.lanhua.result.PageResult;
import com.lanhua.result.Result;
import com.lanhua.service.EmployeeService;
import com.lanhua.utils.JwtUtil;
import com.lanhua.vo.EmployeeLoginVO;
import com.microsoft.schemas.office.visio.x2012.main.PageType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工管理
 */
@Slf4j
@RestController
@RequestMapping("/admin/employee")
@Tag(name = "EmployeeController")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "login请求")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @Operation(summary = "退出登录")
    public Result<String> logout() {
        return Result.success();
    }


    /**
     * 添加员工
     * @param employeeDTO
     * @return
     */
    @PostMapping()
    @Operation(summary = "添加员工")
    public Result add(@RequestBody EmployeeDTO employeeDTO){
        log.info("添加的用户信息，{}",employeeDTO);
        employeeService.add(employeeDTO);
        return Result.success("添加信息成功",null);
    }

    /**
     * 分页查找用户信息
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "分页查看用户")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);

        return Result.success(pageResult);
    }

    /**
     * 启用或停用用户
     * @param status
     * @param id
     * @return
     */
    @PostMapping("status/{status}")
    @Operation(summary = "启用或停用用户")
    public Result startOrStop(@PathVariable Integer status,Long id){
        log.info("启用或停用用户的信息，{}{}",status,id);
        employeeService.starOrStop(status,id);
        return Result.success();

    }

    @GetMapping("/{id}")
    @Operation(summary = "通过id来获取用户信息")
    public Result<Employee> getById(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }


}
