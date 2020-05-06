package cn.liyu.client.fallback;

import cn.liyu.client.ProductService;
import cn.liyu.po.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author liyu
 * @date 2020/4/28 14:52
 * @description
 */
@Service
public class ProductServiceFallBack implements ProductService {
    @Override
    public Product findByPid(Integer pid) {
        Product product = new Product();
        product.setPid(-1);
        return product;
    }

    @Override
    public void reduceInventory(Integer pid, Integer num) {

    }

}
