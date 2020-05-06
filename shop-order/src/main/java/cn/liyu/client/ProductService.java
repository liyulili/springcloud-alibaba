package cn.liyu.client;

import cn.liyu.client.fallback.ProductServiceFallBack;
import cn.liyu.client.fallback.ProductServiceFallBackFactory;
import cn.liyu.po.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author liyu
 * @date 2020/4/28 9:04
 * @description ProductService的feign客户端
 */
@Component
@FeignClient(value = "service-product",//声明调用的提供者的name
//        fallback = ProductServiceFallBack.class,//fallback用于指定容错类
        fallbackFactory = ProductServiceFallBackFactory.class//fallback和fallbackFactory只能使用其中一种方式
)
public interface ProductService {

    @RequestMapping("/product/{pid}")
    Product findByPid(@RequestParam("pid")Integer pid);

    /**
     * 减少库存
     *
     * @param pid
     * @param num
     */
    @PostMapping("/product/reduceInventory")
    void reduceInventory(@RequestParam("pid") Integer pid,
                         @RequestParam("num") Integer num);

}
