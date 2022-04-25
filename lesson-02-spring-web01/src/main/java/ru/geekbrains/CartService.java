package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CartService {
    @Autowired
    private ProductRepository productRepository;

    private Map<Product, Integer> productCount;

    public CartService() {
        this.productCount = new HashMap<>();
    }

    public void insertProduct(Long id, int count) {
        Product product = getProductid(id);
        productCount.merge(product, count, Integer::sum);
    }

    public void deleteProduct(Long id, int count) {
        Product product = getProductid(id);
        Integer curr = productCount.get(product);
        if (curr <= count) {
            productCount.remove(product);
        }else{
            productCount.merge(product, -count, Integer::sum);
        }
    }

    public List<Product> getAll() {
        return new ArrayList<>(productCount.keySet());
    }

    private Product getProductid(Long id) {
        Product product = productRepository.findById(id);
        return product;
    }

    public void delete(Product product) {
        this.productRepository.delete(product.getId());
    }

    public long getCount() {
        return productRepository.getCount();
    }

}
