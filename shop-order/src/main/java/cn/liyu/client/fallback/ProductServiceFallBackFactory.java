//package cn.liyu.client.fallback;
//
//import cn.liyu.client.ProductService;
//import cn.liyu.po.Product;
//import feign.hystrix.FallbackFactory;
//import org.springframework.stereotype.Service;
//
///**
// * @author liyu
// * @date 2020/4/28 14:52
// * @description
// */
//@Service
//public class ProductServiceFallBackFactory implements FallbackFactory<ProductService> {
//
//    @Override
//    public ProductService create(Throwable throwable) {
//        return new ProductService() {
//            @Override
//            public Product findByPid(Integer pid) {
//                throwable.printStackTrace();
//                Product product = new Product();
//                product.setPid(-1);
//                return product;
//            }
//
//            @Override
//            public void reduceInventory(Integer pid, Integer num) {
//                throwable.printStackTrace();
//                System.out.println("reduceInventory异常");
//            }
//
//        };
//    }
//}
