package cn.liyu.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author liyu
 * @date 2020/4/29 16:54
 * @description 自定义全局路由过滤器
 * 有多个路由就需要一个一个来配置，并不能通过像下面这样来实现全局有效（也未在 Fluent Java API 中找到能设置 defaultFilters 的方法）
 *
 * @Bean
 * public ElapsedFilter elapsedFilter(){
 *     return new ElapsedFilter();
 * }
 */
//@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (!StringUtils.isNotBlank(token)) {
            System.out.println("鉴权失败");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            Mono<Void> voidMono = exchange.getResponse().setComplete();
            return voidMono;
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
