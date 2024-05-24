package com.lanhua.controller.user;


import com.lanhua.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Tag(name = "用户店铺接口")
@RequestMapping("/user/shop")
@Controller("userShopController")
public class ShopController {

    private static final String key = "shop_status";

    @Autowired
    private RedisTemplate redisTemplate;
//
//    /**
//     * @param status
//     * @return
//     */
//    @PutMapping("/{status}")
//    public Result setStatus(@PathVariable Integer status) {
//        log.info("设置店铺状态，{}", status);
//        redisTemplate.opsForValue().set(key, status);
//        return Result.success();
//
//    }

    @GetMapping("/status")
    public Result getStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get(key);
        log.info("获取店铺的状态，{}", status == 1 ? "营业中" : "打烊中");
        return Result.success(status);
    }
}
