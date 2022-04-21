package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.geekbrains.controller.ProductSpecifications;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDto> findProductByFilter(String productFilter, Long costMinFilter, Long costMaxFilter, Integer page, Integer size, String sortField) {
        Specification<Product> spec = Specification.where(null);

        if(productFilter != null){
            spec = spec.and(ProductSpecifications.titleContaining(productFilter));
        }

        if(costMinFilter != null){
            spec = spec.and(ProductSpecifications.minCost(costMinFilter));
        }

        if(costMaxFilter != null){
            spec = spec.and(ProductSpecifications.maxCost(costMaxFilter));
        }

        return productRepository.findAll(spec, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC,sortField)))
                .map(ProductServiceImpl::productToDto);

//        return productRepository.findAll(spec).stream()
//                .map(ProductServiceImpl::productToDto)
//                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(ProductServiceImpl::productToDto);
    }

    @Override
    public ProductDto save(ProductDto product) {
        return productToDto(
                productRepository.save(
                        new Product(
                                product.getId(),
                                product.getTitle(),
                                product.getCost()
                        )
                )
        );
    }

    private static ProductDto productToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getCost());

    }
}
