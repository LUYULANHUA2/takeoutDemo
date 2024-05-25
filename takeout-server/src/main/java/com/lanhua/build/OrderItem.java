package com.lanhua.build;

import com.lanhua.entity.OrderDetail;
import com.lanhua.entity.Orders;
import lombok.Data;

import java.util.List;

@Data
public class OrderItem {
    private Orders orders;
    private List<OrderDetail> orderDetailList;
}
