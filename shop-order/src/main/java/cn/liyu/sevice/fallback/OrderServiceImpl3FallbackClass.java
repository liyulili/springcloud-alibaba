package cn.liyu.sevice.fallback;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liyu
 * @date 2020/4/28 14:04
 * @description 降级方法类(方法必须是静态)
 */
@Slf4j
@UtilityClass
public class OrderServiceImpl3FallbackClass {

    /**
     * 发生Throwable时进入的方法
     * @param throwable
     * @return
     */
    public String fallback(Throwable throwable){
        log.error("{}",throwable);
        return "接口发生异常了";
    }
}
