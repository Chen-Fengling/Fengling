package App.service;

import App.domain.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by chenrun on 2017/1/25.
 */

@Service
public class ProductServiceImpl implements ProductService {
    private Map<Long,Product> products = new HashMap<Long, Product>();

    private AtomicLong generator = new AtomicLong();

    @Override
    public Product getId(long id){
        return products.get(id);
    }

    @Override
    public Product add(Product product){
        Long newId = generator.incrementAndGet();
        product.setId(newId);
        products.put(newId,product);
        return product;
    }
    public ProductServiceImpl (){
        Product product = new Product();
        product.setName("Fengling");
        product.setDescription("BestJava");
        product.setPrice(21.75F);
        add(product);
    }
}