package com.lanhua.controller;

import com.lanhua.entity.Employee;
import com.lanhua.result.Result;
import com.lanhua.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public Result list(){

        return Result.success(employeeService.list());
    }
}
