package cn.liyu.sevice.impl;

import cn.liyu.client.ProductService;
import cn.liyu.dao.OrderDao;
import cn.liyu.po.Order;
import cn.liyu.po.Product;
import cn.liyu.sevice.OrderService;
import com.alibaba.fastjson.JSON;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 * @author liyu
 * @date 2020/4/27 15:37
 * @description
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    DataSource dataSource;
    @Autowired
    private ProductService productService;

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public void createOrder1(Order order) {
        orderDao.save(order);
    }


    @GlobalTransactional(rollbackFor = Exception.class)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Order createOrder(Integer pid) throws Exception {
        //1.调用商品微服务查询商品信息
        Product product = productService.findByPid(pid);
        log.info("查询到{}号商品的信息,内容是:{}", pid, JSON.toJSONString(product));

        //2 下单(创建订单)
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderDao.save(order);
        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));

        //3 扣库存
        productService.reduceInventory(pid, order.getNumber());

//        int i = 1 / 0;

        return order;
    }
}

