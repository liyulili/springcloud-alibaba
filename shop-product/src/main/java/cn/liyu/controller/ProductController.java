package cn.liyu.controller;

import cn.liyu.po.Product;
import cn.liyu.sevice.ProductService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author liyu
 * @date 2020/4/27 15:41
 * @description
 */
@RestController
@Slf4j(topic = "ProductController")
public class ProductController {

    @Value("${spring.datasource.url}")
    String url;
    @Autowired
    private ProductService productService;

    @GetMapping("/product/{pid}")
    public Product product(@PathVariable("pid") Integer pid) {
        Product product = productService.findByPid(pid);
        log.info("查询到商品:" + JSON.toJSONString(product));
        return product;
    }

    @GetMapping("/url")
    public String url() {
        return url;
    }

    /**
     * 减少库存
     *
     * @param pid
     * @param num
     */
    @PostMapping("/product/reduceInventory")
    public void reduceInventory(Integer pid, int num) throws Exception {
        productService.reduceInventory(pid, num);
    }

}
