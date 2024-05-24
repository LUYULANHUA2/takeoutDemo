package com.lanhua.mapper;

import com.lanhua.entity.Dish;
import com.lanhua.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    List<SetmealDish> getSetmealIdsByDishIds(List<Long> ids);

    int queryConnect(Dish dish);
}
