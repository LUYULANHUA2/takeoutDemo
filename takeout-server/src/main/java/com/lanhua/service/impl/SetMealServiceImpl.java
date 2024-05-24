package com.lanhua.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lanhua.constant.MessageConstant;
import com.lanhua.dto.SetmealDTO;
import com.lanhua.dto.SetmealPageQueryDTO;
import com.lanhua.entity.Setmeal;
import com.lanhua.entity.SetmealDish;
import com.lanhua.exception.DeletionNotAllowedException;
import com.lanhua.mapper.DishMapper;
import com.lanhua.mapper.SetmealDishMapper;
import com.lanhua.mapper.SetmealMapper;
import com.lanhua.result.PageResult;
import com.lanhua.service.SetMealService;
import com.lanhua.vo.DishItemVO;
import com.lanhua.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class SetMealServiceImpl implements SetMealService {

    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;
    @Autowired
    private DishMapper dishMapper;
    @Override
    public SetmealVO queryById(Long id) {
        SetmealVO setmealVO=setmealMapper.queryById(id);
        return setmealVO;
    }

    @Transactional
    @Override
    public void saveSetMeal(SetmealDTO setmealDTO) {
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        setmealMapper.insert(setmeal);
        if (setmealDishes != null && setmealDishes.size() > 0) {
            for (SetmealDish setmealDish : setmealDishes) {
                setmealDish.setSetmealId(setmeal.getId());
                setmealMapper.saveSetmealDish(setmealDish);
            }
        }
    }

    @Override
    public PageResult page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());
        Page<SetmealVO> page = setmealMapper.pageQuery(setmealPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Transactional
    @Override
    public void updateSetmeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        setmealMapper.update(setmeal);
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if (setmealDishes!=null && setmealDishes.size()>0){
            setmealMapper.deleteSetmealDishesById(setmeal.getId());
            for (SetmealDish setmealDish : setmealDishes) {
                setmealDish.setSetmealId(setmeal.getId());
                setmealMapper.insertSetmealDishes(setmealDish);
            }
        }
    }

    @Transactional
    @Override
    public void delete(List<Long> ids) {
        for (Long id : ids) {
            if(setmealMapper.queryById(id).getStatus()!=0){
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        }
        setmealMapper.delete(ids);
        for (Long id : ids) {
            setmealMapper.deleteSetmealDishesById(id);
        }
    }

    @Override
    public void updateSetmealStatus(int status, Long id) {
        Setmeal setmeal = Setmeal.builder().status(status).id(id).build();
        setmealMapper.update(setmeal);
    }

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    public List<Setmeal> list(Setmeal setmeal) {
        List<Setmeal> list = setmealMapper.list(setmeal);
        return list;
    }

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    public List<DishItemVO> getDishItemById(Long id) {
        return setmealMapper.getDishItemBySetmealId(id);
    }
}
