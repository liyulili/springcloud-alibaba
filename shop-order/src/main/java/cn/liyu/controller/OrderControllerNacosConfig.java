package cn.liyu.controller;

import cn.liyu.client.ProductService;
import cn.liyu.po.Order;
import cn.liyu.po.Product;
import cn.liyu.sevice.OrderService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
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
@Slf4j(topic = "OrderControllerNacosConfig")
@RefreshScope//开启动态读配置
public class OrderControllerNacosConfig {
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    /**
     * 编码方式获取nacos中配置
     *
     * @return
     */
    @GetMapping("/nacos-config-test1")
    public String nacosConfingTest1() {
        return applicationContext.getEnvironment().getProperty("config.appName");
    }

    @Value("${config.appName}")
    private String appName;

    //2 注解方式
    @GetMapping("/nacos-config-test2")
    public String nacosConfingTest2() {
        return appName;
    }

    @Value("${config.env}")
    private String env;

    /** 同一微服务的不同环境下共享配置
     *
     * @return
     */
    @GetMapping("/nacos-config-test3")
    public String nacosConfingTest3() {
        return env;
    }
}
