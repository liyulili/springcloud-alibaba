package cn.liyu.client;

import cn.liyu.po.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author liyu
 * @date 2020/4/28 9:04
 * @description ProductService的feign客户端
 */
@FeignClient("service-product")//声明调用的提供者的name
public interface ProductService {
    //指定调用提供者的哪个方法
    //@FeignClient+@GetMapping 就是一个完整的请求路径 http://serviceproduct/product/{pid}
    @GetMapping(value = "/product/{pid}")
    Product findByPid(@PathVariable("pid") Integer pid);
}
