//package cn.liyu.config;
//
//import com.netflix.loadbalancer.IRule;
//import com.netflix.loadbalancer.RandomRule;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author liyu
// * @date 2020/4/28 8:53
// * @description 增加 Ribbon 负载均衡全局策略配置类
// */
//@Configuration
//public class RibbonGlobalLoadBalancingConfiguration {
//
//    /**
//     * 随机规则
//     */
//    @Bean
//    public IRule ribbonRule() {
//        return new RandomRule();
//    }
//
//}
