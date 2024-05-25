package com.lanhua.service.impl;

import com.lanhua.context.BaseContext;
import com.lanhua.dto.ShoppingCartDTO;
import com.lanhua.entity.Dish;
import com.lanhua.entity.Setmeal;
import com.lanhua.entity.ShoppingCart;
import com.lanhua.mapper.DishMapper;
import com.lanhua.mapper.SetmealMapper;
import com.lanhua.mapper.ShoppingCartMapper;
import com.lanhua.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ShopingCartImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    DishMapper dishMapper;
    @Autowired
    SetmealMapper setmealMapper;

    @Override
    public void add(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        shoppingCart.setUserId(BaseContext.getCurrentId());
        //
        //判断表中是否已经有该用户的数据
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.select(shoppingCart);
        if (shoppingCartList != null && !shoppingCartList.isEmpty()) {
            //修改数据
            ShoppingCart cart = shoppingCartList.get(0);
            cart.setNumber(cart.getNumber()+1);
            shoppingCartMapper.updateShoppingCart(cart);
        } else {
            //添加数据

            //判断当前添加到购物车的是菜品还是套餐
            Long dishId = shoppingCartDTO.getDishId();
            if (dishId != null) {
                //添加到购物车的是菜品
                insertDish(shoppingCart);
            } else {
                //添加到购物车的是套餐
                insertSetmeal(shoppingCart);
            }
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCart.setNumber(1);
            shoppingCartMapper.insert(shoppingCart);

        }
    }

    @Override
    public List<ShoppingCart> checkShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(BaseContext.getCurrentId());
        return shoppingCartMapper.checkShoppingCartList(shoppingCart);
    }

    @Override
    public void cleanShoppingCart() {
        ShoppingCart shoppingCart = ShoppingCart.builder().userId(BaseContext.getCurrentId()).build();
        shoppingCartMapper.deleteByUserId(shoppingCart);
    }

    @Override
    public void substanceShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
        shoppingCart.setUserId(BaseContext.getCurrentId());
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.checkShoppingCartList(shoppingCart);
        ShoppingCart shoppingCartChecked = shoppingCartList.get(0);
        if (shoppingCartChecked.getNumber()>1){
            shoppingCartChecked.setNumber(shoppingCartChecked.getNumber() - 1);
            shoppingCartMapper.updateShoppingCart(shoppingCartChecked);
        }else shoppingCartMapper.deleteShoppingCart(shoppingCartChecked);
    }
    private void insertDish(ShoppingCart shoppingCart){
        Dish dish = dishMapper.getById(shoppingCart.getDishId());
        shoppingCart.setName(dish.getName());
        shoppingCart.setImage(dish.getImage());
        shoppingCart.setAmount(dish.getPrice());
    }

    private void insertSetmeal(ShoppingCart shoppingCart){
        Setmeal setmeal = setmealMapper.querySetMealById(shoppingCart.getSetmealId());
        shoppingCart.setName(setmeal.getName());
        shoppingCart.setImage(setmeal.getImage());
        shoppingCart.setAmount(setmeal.getPrice());
    }
}
