package com.lanhua.mapper;

import com.github.pagehelper.Page;
import com.lanhua.annotation.AutoFill;
import com.lanhua.dto.DishPageQueryDTO;
import com.lanhua.entity.Dish;
import com.lanhua.enumeration.OperationType;
import com.lanhua.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void delete(List<Long> ids);

    //@Select("select status from dish where id=#{id}")
    Dish getById(Long id);

    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);


    List<Dish> queryByCategoryId(Dish dish);

    @Select("select count(status) from dish where status=1")
    Integer queryStatus_ON();
    @Select("select count(status) from dish where status=0")
    Integer queryStatus_OFF();
}
