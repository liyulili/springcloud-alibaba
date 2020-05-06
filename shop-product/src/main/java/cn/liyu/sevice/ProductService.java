package cn.liyu.sevice;

import cn.liyu.po.Product;

/**
 * @author liyu
 * @date 2020/4/27 15:36
 * @description
 */
public interface ProductService {

    /**
     * 通过商品主键查询商品
     * @param pid
     * @return
     */
    public Product findByPid(Integer pid);

    /**
     * 减少库存
     * @param pid
     * @param num
     */
    public void reduceInventory(Integer pid, int num) throws Exception;
}
