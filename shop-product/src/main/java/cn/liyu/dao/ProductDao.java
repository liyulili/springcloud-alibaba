package cn.liyu.dao;

import cn.liyu.po.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liyu
 * @date 2020/4/27 15:34
 * @description
 */
public interface ProductDao extends JpaRepository<Product, Integer> {
}
