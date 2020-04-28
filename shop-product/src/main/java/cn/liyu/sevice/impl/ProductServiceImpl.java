package cn.liyu.sevice.impl;

import cn.liyu.po.Product;
import cn.liyu.dao.ProductDao;
import cn.liyu.sevice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return productDao.findById(pid).orElseGet(()->new Product().setPname("商品不存在"));
    }
}
