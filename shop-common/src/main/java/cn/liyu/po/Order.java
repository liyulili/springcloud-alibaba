package cn.liyu.po;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author liyu
 * @date 2020/4/27 15:14
 * @description 订单
 */
@Entity(name = "shop_order")
@Data
@Accessors(chain = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 订单id
     */
    private Long oid;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 商品id
     */
    private Integer pid;
    /**
     * 商品名称
     */
    private String pname;
    /**
     * 商品单价
     */
    private Double pprice;
    /**
     * 购买数量
     */
    private Integer number;
}