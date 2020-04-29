package cn.liyu.config;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author liyu
 * @date 2020/4/29 15:45
 * @description 自定义一个断言工厂, 实现断言方法(方法名和配置有关，不能随意)
 * http://localhost:7000/product-serv/product/1?age=3
 */
@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    /**
     * 断言实现
     *
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        return serverWebExchange -> {
            //从serverWebExchange获取传入的参数
            String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");
            if (StringUtils.isNotBlank(ageStr)) {
                int age = Integer.parseInt(ageStr);
                return age > config.getMinAge() && age < config.getMaxAge();
            }
            return true;
        };
    }

    /**
     * 用于从配置文件中获取参数值赋值到配置类中的属性上
     *
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        //顺序要跟配置文件中的参数顺序一致
        return Arrays.asList("minAge", "maxAge");
    }

    /**
     * 自定义配置类, 用于接收配置文件中的参数
     */
    @Data
    public static class Config {
        private int minAge;
        private int maxAge;
    }
}
