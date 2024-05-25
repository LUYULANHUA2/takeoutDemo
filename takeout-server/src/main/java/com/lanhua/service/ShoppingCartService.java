package com.lanhua.service;


import com.lanhua.dto.ShoppingCartDTO;
import com.lanhua.entity.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoppingCartService {
    void add(ShoppingCartDTO shoppingCartDTO);


    List<ShoppingCart> checkShoppingCart();

    void cleanShoppingCart();

    void substanceShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
