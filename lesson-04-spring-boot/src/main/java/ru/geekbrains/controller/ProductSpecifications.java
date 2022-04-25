package ru.geekbrains.controller;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.Product;

public final class ProductSpecifications {
    public static Specification<Product> titleContaining(String title){
        return (root, query, cb) -> cb.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Product> minCost(Long minCost){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), minCost));
    }

    public static Specification<Product> maxCost(Long maxCost){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxCost));
    }
}
