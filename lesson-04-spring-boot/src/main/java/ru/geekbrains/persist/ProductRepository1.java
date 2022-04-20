package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository1 extends JpaRepository<Product, Long> {
//    List<Product> findProductByNameLike(String productName);


    @Query("select p from Product p " +
            "where (p.title like :productname or :productname is null) and " +
            "      (p.cost >= :minCost or :minCost is null) and " +
            "      (p.cost <= :maxCost or :maxCost is null)")
    List<Product> findWithFilter(@Param("productname") String productFilter,
                                 @Param("minCost") Long minCost,
                                 @Param("maxCost") Long maxCost);
}
