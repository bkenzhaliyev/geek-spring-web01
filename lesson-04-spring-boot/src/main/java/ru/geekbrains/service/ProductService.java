package ru.geekbrains.service;


import org.springframework.data.domain.Page;
import ru.geekbrains.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<ProductDto> findProductByFilter(String productFilter,
                                         Long minCost,
                                         Long maxCost,
                                         Integer page,
                                         Integer size,
                                         String sortField);
    Optional<ProductDto> findById(Long id);
    ProductDto save(ProductDto product);
    void deleteById(Long id);

}
