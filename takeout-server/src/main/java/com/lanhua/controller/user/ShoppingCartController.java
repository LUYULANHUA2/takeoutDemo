package com.lanhua.controller.user;


import com.lanhua.dto.ShoppingCartDTO;
import com.lanhua.entity.ShoppingCart;
import com.lanhua.result.Result;
import com.lanhua.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "C端-购物车相关接口")
@RequestMapping("/user/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Operation(summary = "添加餐品到购物车")
    @PostMapping("/add")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){
        shoppingCartService.add(shoppingCartDTO);
        return Result.success();
    }


    @GetMapping("/list")
    @ApiOperation("查看购物车")
    public Result<List<ShoppingCart>> checkShoppingCart(){
        log.info("查看购物车");
        List<ShoppingCart> shoppingCartList=shoppingCartService.checkShoppingCart();
        return Result.success(shoppingCartList);
    }

    @DeleteMapping("/clean")
    @ApiOperation("清空购物车")
    public Result cleanShoppingCart(){
        log.info("开始清空购物车");
        shoppingCartService.cleanShoppingCart();
        return Result.success();
    }

    @PostMapping("/sub")
    @ApiOperation("减少购物车数量")
    public Result substanceShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("减少购物车货物数量");
        shoppingCartService.substanceShoppingCart(shoppingCartDTO);
        return Result.success();

    }
}
