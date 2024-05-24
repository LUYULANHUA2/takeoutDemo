package com.lanhua.controller.admin;


import com.lanhua.dto.DishDTO;
import com.lanhua.dto.DishPageQueryDTO;
import com.lanhua.entity.Dish;
import com.lanhua.result.PageResult;
import com.lanhua.result.Result;
import com.lanhua.service.DishService;
import com.lanhua.vo.DishVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RestController("adminDishController")
@RequestMapping("/admin/dish")
public class DishController {

    private static final String dishName = "dish_*";


    @Autowired
    DishService dishService;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 新增菜品
     *
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增菜品")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品{}", dishDTO);
        dishService.save(dishDTO);
        //清理缓存
        cleanCache(String.valueOf(dishDTO.getId()));
        return Result.success();
    }

    /**
     * 菜品分页
     *
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("菜品分页")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("分页查询：{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 批量删除菜品
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("批量删除菜品")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("菜品批量删除：{}", ids);
        dishService.delete(ids);
        cleanCache(dishName);
        return Result.success();
    }

    /**
     * 回显
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("根据id查询菜品");
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * 修改菜品
     *
     * @param dishDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品");
        dishService.updateWithFlavor(dishDTO);
        cleanCache(dishName);
        return Result.success();
    }


    /**
     * 根据分类查询菜品
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类查询菜品")
    public Result<List<Dish>> queryByCategoryId(Long categoryId) {
        //构造redis中的key，规则：dish_分类id
        log.info("根据分类查询菜品{}", categoryId);
        List<Dish> dish = dishService.queryByCategoryId(categoryId);
        return Result.success(dish);
    }

    @PostMapping("status/{status}")
    @ApiOperation("菜品起售、停售")
    public Result updateDishStatus(@PathVariable int status, Long id) {
        log.info("菜品起售、停售");

        dishService.updateDishStatus(status, id);
        cleanCache(dishName);
        return Result.success();
    }

    private void cleanCache(String pattern) {
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
        log.info("清理缓存,{}",keys);
    }
}
