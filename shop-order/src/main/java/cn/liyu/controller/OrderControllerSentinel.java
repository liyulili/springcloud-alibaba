package cn.liyu.controller;

import cn.liyu.client.ProductService;
import cn.liyu.po.Order;
import cn.liyu.po.Product;
import cn.liyu.sevice.OrderService;
import cn.liyu.sevice.impl.OrderServiceImpl3;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyu
 * @date 2020/4/27 16:14
 * @description Sentinel测试
 */
@RestController
@Slf4j(topic = "OrderControllerSentinel")
public class OrderControllerSentinel {


    @Autowired
    private OrderServiceImpl3 orderService;

    @Autowired
    private ProductService productService;

    int i = 0;

    /**
     * 测试流控 直接方式
     *
     * @return
     */
    @RequestMapping("/order/message1")
    public String message1() {
        return "message1";
    }

    /**
     * 测试流控，关联资源
     *
     * @return
     */
    @RequestMapping("/order/message2")
    public String message2() {
        return "message2";
    }

    /**
     * 测试降级 RT,异常比例，异常数
     *
     * @return
     */
    @RequestMapping("/order/message3")
    public String message3() {
        i++;
        if (i % 3 == 0) {
            throw new RuntimeException();
        }
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "message3";
    }

    /**
     * 测试热点规则
     * 热点参数流控规则是一种更细粒度的流控规则, 它允许将规则具体到参数上
     *
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("/order/message4")
    @SentinelResource("message4")//注意这里必须使用这个注解标识,热点规则不生效
    public String message4(String name, Integer age) {
        return name + age;
    }

    /**
     * 测试熔断降级，异常自定义处理
     * @return
     */
    @RequestMapping("/order/message5")
    public String message() {
        return orderService.message();
    }
}
