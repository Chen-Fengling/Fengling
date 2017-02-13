package App.service;

import App.domain.Product;

/**
 * Created by chenrun on 2017/1/25.
 */
public interface ProductService {
    Product add(Product product);
    Product getId(long id);
}
