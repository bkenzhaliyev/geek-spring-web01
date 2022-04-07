package ru.geekbrains;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void insert(Product product) {
        this.productRepository.insert(product);
    }

    public void delete(long id) {
        this.productRepository.delete(id);
    }

    public long getCount() {
        return productRepository.getCount();
    }
}
