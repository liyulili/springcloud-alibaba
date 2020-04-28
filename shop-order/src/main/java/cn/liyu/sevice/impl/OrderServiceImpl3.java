package cn.liyu.sevice.impl;

import cn.liyu.sevice.fallback.OrderServiceImpl3FallbackClass;
import cn.liyu.sevice.handler.OrderServiceImpl3BlockHandlerClass;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liyu
 * @date 2020/4/28 11:38
 * @description 熔断降级处理测试
 */
@Service
@Slf4j
public class OrderServiceImpl3 {
    int i;

    //    @SentinelResource(value = "message",
//            blockHandler = "messageBlockHandler",//指定发生BlockException时进入的方法
//            fallback = "fallback"//指定发生Throwable时进入的方法
//    )
    @SentinelResource(value = "message",
            blockHandler = "messageBlockHandler",//指定发生BlockException时进入的方法
            blockHandlerClass = OrderServiceImpl3BlockHandlerClass.class,
            fallback = "fallback",//指定发生Throwable时进入的方法
            fallbackClass = OrderServiceImpl3FallbackClass.class
    )
    public String message() {
        i++;
        if (i % 3 == 0) {
            throw new RuntimeException();
        }
        return "message";
    }

//    /**
//     * BlockException时进入的方法
//     * @param ex
//     * @return
//     */
//    public String messageBlockHandler(BlockException ex){
//        log.error("{}",ex);
//        return "接口被限流或者降级了。。。";
//    }
//
//    /**
//     * 发生Throwable时进入的方法
//     * @param throwable
//     * @return
//     */
//    public String fallback(Throwable throwable){
//        log.error("{}",throwable);
//        return "接口发生异常了";
//    }
}
