package ru.geekbrains.persist;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
    private Map<Long, Product> productMap = new ConcurrentHashMap<>();
    private AtomicLong identity = new AtomicLong(0);

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }


    @PostConstruct
    public void init() {
        this.save(new Product("Компьютер HP D600", 45000));
        this.save(new Product("Компьютер HP D600", 45000));
        this.save(new Product("Компьютер HP D600", 45000));
        this.save(new Product("Компьютер HP D600", 45000));
        this.save(new Product("Монитор LG G2100", 12300));
        this.save(new Product("Монитор LG G2100", 12300));
        this.save(new Product("Монитор LG G2100", 12300));
        this.save(new Product("Монитор LG G2100", 12300));
        this.save(new Product("Ноутбук Dell Vostro 650", 87950));
        this.save(new Product("Ноутбук Dell Vostro 650", 87950));
        this.save(new Product("Ноутбук Dell Vostro 650", 87950));
        this.save(new Product("Телевизор Sumsung DS5600D", 76000));
    }

    @Override
    public Product findById(Long id) {
        return productMap.get(id);
    }

    @Override
    public void update(Product product) {
        productMap.put(product.getId(), product);
    }

    @Override
    public void delete(Long id) {
        productMap.remove(id);
    }

    @Override
    public Product save(Product product) {
        if(product.getId() == null){
            product.setId(identity.incrementAndGet());
        }
        productMap.put(product.getId(), product);
        return product;
    }

    @Override
    public long getCount() {
        return productMap.size();
    }
}
