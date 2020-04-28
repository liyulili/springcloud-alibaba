package cn.liyu.po;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author liyu
 * @date 2020/4/27 15:12
 * @description 商品
 */
@Entity(name = "shop_product")
@Data
@Accessors(chain = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 主键
     */
    private Integer pid;
    /**
     * 商品名称
     */
    private String pname;
    /**
     * 商品价格
     */
    private Double pprice;
    /**
     * 库存
     */
    private Integer stock;

}