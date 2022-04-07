package ru.geekbrains.persist;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    Product findById(Long id);

    void update(Product product);

    void delete(Long id);

    Product save(Product product);

    long getCount();
}
