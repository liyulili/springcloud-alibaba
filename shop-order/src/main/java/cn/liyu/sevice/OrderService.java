package cn.liyu.sevice;

import cn.liyu.po.Order;

/**
 * @author liyu
 * @date 2020/4/27 15:36
 * @description
 */
public interface OrderService {

    /**
     * 保存订单
     *
     * @param order
     * @return
     */
    public void save(Order order);

    /**
     * 创建订单
     * @param order
     */
    void createOrder(Order order);
}
