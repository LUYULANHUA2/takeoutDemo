package com.lanhua.service;

import com.lanhua.dto.SetmealDTO;
import com.lanhua.dto.SetmealPageQueryDTO;
import com.lanhua.entity.Setmeal;
import com.lanhua.result.PageResult;
import com.lanhua.vo.DishItemVO;
import com.lanhua.vo.SetmealVO;

import java.util.List;

public interface SetMealService {
    SetmealVO queryById(Long id);

    void saveSetMeal(SetmealDTO setmealDTO);

    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    void updateSetmeal(SetmealDTO setmealDTO);

    void delete(List<Long> ids);

    void updateSetmealStatus(int status, Long id);

        /**
         * 条件查询
         * @param setmeal
         * @return
         */
    List<Setmeal> list(Setmeal setmeal);

        /**
         * 根据id查询菜品选项
         * @param id
         * @return
         */
    List<DishItemVO> getDishItemById(Long id);

}
