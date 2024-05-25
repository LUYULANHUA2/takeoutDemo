package com.lanhua.mapper;

import com.lanhua.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    List<ShoppingCart> select(ShoppingCart shoppingCart);

    void updateShoppingCart(ShoppingCart cart);

    void insert(ShoppingCart shoppingCart);

    List<ShoppingCart> checkShoppingCartList(ShoppingCart shoppingCart);

    void deleteByUserId(ShoppingCart shoppingCart);

    void deleteShoppingCart(ShoppingCart shoppingCartChecked);
}
