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
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private ProductService productService;

    /**
     * 手动拼接url
     *
     * @param pid
     * @return
     */
//    @GetMapping("/order/prod/{pid}")
    public Order orderRestTemplate(@PathVariable("pid") Integer pid) {
        log.info(">>客户下单， 这时候要调用商品微服务查询商品信息");
        //通过restTemplate调用商品微服务
        Product product = restTemplate.getForObject("http://localhost:8081/product/" + pid, Product.class);
        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));
        Order order = new Order()
                .setUid(1)
                .setUsername("测试用户")
                .setPid(product.getPid())
                .setPname(product.getPname())
                .setPprice(product.getPprice())
                .setNumber(1);
        orderService.save(order);
        return order;
    }

    /**
     * Nacos实例获取url
     *
     * @param pid
     * @return
     */
//    @GetMapping("/order/prod/{pid}")
    public Order orderNacos(@PathVariable("pid") Integer pid) {
        log.info(">>客户下单， 这时候要调用商品微服务查询商品信息");

        //从nacos中获取服务地址
        ServiceInstance serviceInstance = discoveryClient.getInstances("service-product").get(0);
//        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        String url = serviceInstance.getUri().toString();
        log.info(">>从nacos中获取到的微服务地址为:" + url);


        //通过restTemplate调用商品微服务
        Product product = restTemplate.getForObject(url + "/product/" + pid, Product.class);
        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));
        Order order = new Order()
                .setUid(1)
                .setUsername("测试用户")
                .setPid(product.getPid())
                .setPname(product.getPname())
                .setPprice(product.getPprice())
                .setNumber(1);
        orderService.save(order);
        return order;
    }

    /**
     * 自定义随机负载均衡
     *
     * @param pid
     * @return
     */
//    @GetMapping("/order/prod/{pid}")
    public Order orderRandomBalance(@PathVariable("pid") Integer pid) {
        log.info(">>客户下单， 这时候要调用商品微服务查询商品信息");
        //从nacos中获取服务地址
        //自定义规则实现随机挑选服务第3步：启动两个服务提供者和一个服务消费者，多访问几次消费者测试效果

        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        int index = new Random().nextInt(instances.size());
        ServiceInstance serviceInstance = instances.get(index);
        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        log.info(">>从nacos中获取到的微服务地址为:" + url);

        //通过restTemplate调用商品微服务
        Product product = restTemplate.getForObject("http://" + url + "/product/" + pid, Product.class);
        log.info(">>商品信息， 查询结果:" + JSON.toJSONString(product));
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.save(order);
        return order;
    }

    /**
     * 使用ribbon负载均衡
     *
     * @param pid
     * @return
     */
//    @GetMapping("/order/prod/{pid}")
    public Order orderRibbon(@PathVariable("pid") Integer pid) {
        log.info(">>客户下单， 这时候要调用商品微服务查询商品信息");
        //直接使用微服务名字， 从nacos中获取服务地址
        String url = "service-product";
        //通过restTemplate调用商品微服务
        Product product = restTemplate.getForObject("http://" + url + "/product/" + pid, Product.class);
        log.info(">>商品信息， 查询结果:" + JSON.toJSONString(product));
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.save(order);
        return order;
    }

    /**
     * 使用feign调用
     * @param pid
     * @return
     */
    @GetMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info(">>客户下单,这时候要调用商品微服务查询商品信息");
        //通过fegin调用商品微服务
        Product product = productService.findByPid(pid);
        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.save(order);
        return order;
    }
}
