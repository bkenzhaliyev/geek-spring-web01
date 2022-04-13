package ru.geekbrains;

import ru.geekbrains.model.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "Product.findById", query = "SELECT p FROM products p"),
        @NamedQuery(name = "List", query = "SELECT p FROM products p"),
        @NamedQuery(name = "findAll", query = "SELECT p FROM Products p"),
        @NamedQuery(name = "deleteById", query = "Delete p FROM Products p WHERE p.id = :id")
})
public class ProductDao {
    private Map<Long, Product> productMap = new ConcurrentHashMap<>();
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Product findById(EntityManagerFactory factory, Long id) {
        EntityManager em = factory.createEntityManager();

        Product product = null;
        em.getTransaction().begin();
        product = em.createNamedQuery("Product.findById", Product.class).setParameter("id", 1).getSingleResult();
        em.getTransaction().commit();

        return product;
    }
}
