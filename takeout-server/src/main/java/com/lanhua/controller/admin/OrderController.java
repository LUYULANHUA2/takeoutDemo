package com.lanhua.controller.admin;

import com.lanhua.dto.OrdersCancelDTO;
import com.lanhua.dto.OrdersConfirmDTO;
import com.lanhua.dto.OrdersPageQueryDTO;
import com.lanhua.dto.OrdersRejectionDTO;
import com.lanhua.result.PageResult;
import com.lanhua.result.Result;
import com.lanhua.service.OrderService;
import com.lanhua.vo.OrderStatisticsVO;
import com.lanhua.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Api("订单管理接口")
@RestController("adminOrderController")
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/conditionSearch")
    public Result<PageResult> conditionSearchOrders(OrdersPageQueryDTO ordersPageQueryDTO){
        log.info("订单搜索");
        PageResult pageResult=orderService.conditionSearchOrders(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/details/{id}")
    public Result<OrderVO> searchOrderDetails(@PathVariable Long id){
        log.info("订单明细查询");
        OrderVO orderVO = orderService.checkOrderDetail(id);
        return Result.success(orderVO);
    }

    @PutMapping("/cancel")
    public Result cancelOrdersBackend(@RequestBody OrdersCancelDTO ordersCancelDTO){
        log.info("订单取消");
        orderService.cancelOrderBackend(ordersCancelDTO);
        return Result.success();
    }

    @PutMapping("/rejection")
    public Result rejectionOrders(@RequestBody OrdersRejectionDTO ordersRejectionDTO) throws Exception {
        log.info("拒绝订单");
        orderService.rejectionOrders(ordersRejectionDTO);
        return Result.success();
    }

    /**
     * 接单
     *
     * @return
     */
    @PutMapping("/confirm")
    @ApiOperation("接单")
    public Result confirm(@RequestBody OrdersConfirmDTO ordersConfirmDTO) {
        orderService.confirm(ordersConfirmDTO);
        return Result.success();
    }

    /**
     * 派送订单
     *
     * @return
     */
    @PutMapping("/delivery/{id}")
    @ApiOperation("派送订单")
    public Result delivery(@PathVariable Long id) {
        orderService.delivery(id);
        return Result.success();
    }

    /**
     * 完成订单
     *
     * @return
     */
    @PutMapping("/complete/{id}")
    @ApiOperation("完成订单")
    public Result complete(@PathVariable("id") Long id) {
        orderService.complete(id);
        return Result.success();
    }

    @GetMapping("/statistics")
    public Result<OrderStatisticsVO> queryOrderStatistics(){
        log.info("各个状态的订单数量统计");
        OrderStatisticsVO statisticsVO=orderService.queryOrderStatistics();
        return Result.success(statisticsVO);
    }
}
