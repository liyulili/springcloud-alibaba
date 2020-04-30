package cn.liyu.sevice.impl;

import cn.liyu.dao.OrderDao;
import cn.liyu.dao.TxLogDao;
import cn.liyu.po.Order;
import cn.liyu.po.TxLog;
import cn.liyu.sevice.fallback.OrderServiceImpl3FallbackClass;
import cn.liyu.sevice.handler.OrderServiceImpl3BlockHandlerClass;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @author liyu
 * @date 2020/4/28 11:38
 * @description 事务性消息
 */
@Service
public class OrderServiceImpl4 {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private TxLogDao txLogDao;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    public void createOrderBefore(Order order) {

        String txId = UUID.randomUUID().toString();

        //发送半事务消息
        rocketMQTemplate.sendMessageInTransaction(
                "tx_producer_group",
                "tx_topic",
                MessageBuilder.withPayload(order).setHeader("txId", txId).build(),
                order
        );
    }

    @Transactional
    public void createOrder(String txId, Order order) {
        //保存订单
        orderDao.save(order);

        TxLog txLog = new TxLog();
        txLog.setTxId(txId);
        txLog.setDate(new Date());

        //记录事物日志
        txLogDao.save(txLog);
    }
}
