package com.lanhua.service;

import com.lanhua.dto.DishDTO;
import com.lanhua.dto.DishPageQueryDTO;
import com.lanhua.entity.Dish;
import com.lanhua.result.PageResult;
import com.lanhua.vo.DishVO;

import java.util.List;

public interface DishService {
    void save(DishDTO dishDTO);

    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void delete(List<Long> ids);

    DishVO getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDTO dishDTO);

    List<Dish> queryByCategoryId(Long id);

    void updateDishStatus(int status,Long id);

    List<DishVO> listWithFlavor(Dish dish);
}
