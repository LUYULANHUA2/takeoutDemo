package com.lanhua.service;

import com.lanhua.dto.*;
import com.lanhua.result.PageResult;
import com.lanhua.vo.OrderPaymentVO;
import com.lanhua.vo.OrderStatisticsVO;
import com.lanhua.vo.OrderSubmitVO;
import com.lanhua.vo.OrderVO;

public interface OrderService {
    OrderSubmitVO oderSubmit(OrdersSubmitDTO ordersSubmitDTO);
    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    PageResult checkHistoryOrders(OrdersPageQueryDTO ordersPageQueryDTO);

    OrderVO checkOrderDetail(Long id);

    void cancelOrder(Long id) throws Exception;

    void orderAgain(Long id);

    PageResult conditionSearchOrders(OrdersPageQueryDTO ordersPageQueryDTO);

    void cancelOrderBackend(OrdersCancelDTO ordersCancelDTO);

    void rejectionOrders(OrdersRejectionDTO ordersRejectionDTO) throws Exception;

    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    void delivery(Long id);

    void complete(Long id);

    OrderStatisticsVO queryOrderStatistics();

    void reminder(Long id);
}
