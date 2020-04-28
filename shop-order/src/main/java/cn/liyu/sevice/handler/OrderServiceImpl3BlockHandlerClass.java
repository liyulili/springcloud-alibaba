package cn.liyu.sevice.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liyu
 * @date 2020/4/28 14:04
 * @description 熔断方法类(方法必须是静态)
 */
@Slf4j
@UtilityClass
public class OrderServiceImpl3BlockHandlerClass {
    /**
     * BlockException时进入的方法
     *
     * @param ex
     * @return
     */
    public String messageBlockHandler(BlockException ex) {
        log.error("{}", ex);
        return "接口被限流或者降级了。。。";
    }
}
