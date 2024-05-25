package com.lanhua.controller.user;


import com.lanhua.dto.OrdersPageQueryDTO;
import com.lanhua.dto.OrdersPaymentDTO;
import com.lanhua.dto.OrdersSubmitDTO;
import com.lanhua.result.PageResult;
import com.lanhua.result.Result;
import com.lanhua.service.OrderService;
import com.lanhua.vo.OrderPaymentVO;
import com.lanhua.vo.OrderSubmitVO;
import com.lanhua.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userOrderController")
@Slf4j
@RequestMapping("/user/order")
@Api(tags = "C端-用户点单接口")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/submit")
    @ApiOperation("用户下单")
    public Result<OrderSubmitVO> oderSubmit(@RequestBody OrdersSubmitDTO ordersSubmitDTO){
        log.info("用户下单{}",ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO = orderService.oderSubmit(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }
    /**
     * 订单支付
     *
     * @param ordersPaymentDTO
     * @return
     */
    @PutMapping("/payment")
    @ApiOperation("订单支付")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        log.info("订单支付：{}", ordersPaymentDTO);
        OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
        log.info("生成预支付交易单：{}", orderPaymentVO);
        return Result.success(orderPaymentVO);
    }

    @GetMapping("/historyOrders")
    @ApiOperation("历史订单查询")
    public Result<PageResult> checkHistoryOrders(OrdersPageQueryDTO ordersPageQueryDTO){
        log.info("查询历史订单");
        PageResult pageResult=orderService.checkHistoryOrders(ordersPageQueryDTO);
        return Result.success(pageResult);
    }


    @GetMapping("orderDetail/{id}")
    @ApiOperation("查询订单详情")
    public Result<OrderVO> checkOrderDetail(@PathVariable Long id){
        log.info("查询订单详情");
        OrderVO orderDetailList=orderService.checkOrderDetail(id);
        return Result.success(orderDetailList);
    }

    @PutMapping("/cancel/{id}")
    @ApiOperation("取消订单")
    public Result cancelOrder(@PathVariable Long id) throws Exception {
        log.info("取消订单");
        orderService.cancelOrder(id);
        return Result.success();
    }


}
