package cn.liyu.sevice.impl;

import cn.liyu.po.Product;
import cn.liyu.dao.ProductDao;
import cn.liyu.sevice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liyu
 * @date 2020/4/27 15:37
 * @description
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).orElseGet(() -> new Product().setPname("商品不存在"));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void reduceInventory(Integer pid, int num) throws Exception {
        Product product = productDao.findById(pid).get();
        if (product.getStock() >= num) {
            product.setStock(product.getStock() - num);//减库存
            int i = 1 / 0;
            productDao.save(product);
        } else {
            throw new Exception("库存不足");
        }
    }
}
