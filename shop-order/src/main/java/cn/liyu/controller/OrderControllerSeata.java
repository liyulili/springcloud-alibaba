package cn.liyu.controller;

import cn.liyu.client.ProductService;
import cn.liyu.po.Order;
import cn.liyu.po.Product;
import cn.liyu.sevice.OrderService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * @author liyu
 * @date 2020/4/27 16:14
 * @description
 */
@RestController
@Slf4j(topic = "OrderController")
public class OrderControllerSeata {

    @Autowired
    private OrderService orderService;




    /**
     * Seata分布式事务
     * 下单
     *
     * @param pid
     * @return
     */
    @GetMapping("/order/prod/{pid}")
    public Order orderFeignSentinel(@PathVariable("pid") Integer pid) throws Exception {
        log.info(">>客户下单");
        Order order = orderService.createOrder(pid);
        return order;
    }
}
