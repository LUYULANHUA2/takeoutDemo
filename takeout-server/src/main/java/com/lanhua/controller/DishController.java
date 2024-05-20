package com.lanhua.controller;


import com.lanhua.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/user/dish")
public class DishController {



    @PostMapping
    public Result list(){

        return Result.success();
    }
}
