package cn.liyu.sevice.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author liyu
 * @date 2020/4/28 11:38
 * @description 链路流控模式(要配置禁止收敛URL的入口 context)
 * 链路流控模式指的是，当从某个接口过来的资源达到限流条件时，开启限流。它的功能有点类似于针对
 * 来源配置项，区别在于： 针对来源是针对上级微服务，而链路流控是针对上级接口，也就是说它的粒度
 * 更细。
 */
@Service
public class OrderServiceImpl2 {

    @SentinelResource("message")
    public void message() {
        System.out.println("message");
    }
}
