package com.lanhua.controller.admin;

import com.lanhua.dto.SetmealDTO;
import com.lanhua.dto.SetmealPageQueryDTO;
import com.lanhua.result.PageResult;
import com.lanhua.result.Result;
import com.lanhua.service.SetMealService;
import com.lanhua.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "套餐分类接口")
@RestController("adminSetmealController")
@RequestMapping("/admin/setmeal")
public class SetMealController {
    @Autowired
    SetMealService setMealService;

    @ApiOperation("根据id查询套餐")
    @GetMapping("/{id}")
    public Result<SetmealVO> queryById(@PathVariable Long id){
        log.info("根据id查询套餐");
        SetmealVO setmealVO=setMealService.queryById(id);
        return Result.success(setmealVO);
    }

    @ApiOperation("新增套餐")
    @CacheEvict(cacheNames = "setmealCache",key = "#setmealDTO.categoryId")
    @PostMapping
    public Result saveSetMeal(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐");
        setMealService.saveSetMeal(setmealDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分页查询套餐")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("分页查询套餐{}",setmealPageQueryDTO);
        PageResult pageResult=setMealService.page(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    @PutMapping
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    @ApiOperation("修改套餐")
    public Result updateSetmeal(@RequestBody SetmealDTO setmealDTO){
        log.info("修改套餐{}",setmealDTO);
        setMealService.updateSetmeal(setmealDTO);
        return  Result.success();
    }

    @DeleteMapping
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    @ApiOperation("批量删除套餐")
    public Result delete(@RequestParam List<Long> ids){
        log.info("批量删除套餐{}",ids);
        setMealService.delete(ids);
        return Result.success();
    }

    @PostMapping("status/{status}")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    @ApiOperation("套餐起售、停售")
    public Result updateSetmealStatus(@PathVariable int status,Long id){
        log.info("套餐起售、停售{}",id);
        setMealService.updateSetmealStatus(status,id);
        return Result.success();
    }


}
