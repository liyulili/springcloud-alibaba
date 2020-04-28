package cn.liyu.sevice.impl;

import cn.liyu.dao.OrderDao;
import cn.liyu.po.Order;
import cn.liyu.sevice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liyu
 * @date 2020/4/27 15:37
 * @description
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public void createOrder(Order order) {
        orderDao.save(order);
    }
}
