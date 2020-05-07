package cn.liyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author liyu
 * @date 2020/4/27 15:31
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
