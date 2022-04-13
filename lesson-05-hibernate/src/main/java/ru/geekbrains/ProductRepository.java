package ru.geekbrains;

import org.springframework.stereotype.Repository;
import ru.geekbrains.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final EntityManagerFactory eFactory;


    public ProductRepository(EntityManagerFactory eFactory) {
        this.eFactory = eFactory;
    }

    public Optional<Product> findById(Long id) {
        EntityManager em = eFactory.createEntityManager();
        try {
            return Optional.ofNullable(em.find(Product.class, id));
        } finally {
            em.close();
        }
    }

    public List<Product> findAll() {
        EntityManager em = eFactory.createEntityManager();
        try {
            List<Product> productList = em.createNamedQuery("findAll", Product.class).getResultList();
            return productList;
        } finally {
            em.close();
        }
    }

    public void delete(long id) {
        EntityManager em = eFactory.createEntityManager();
        try {
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.getTransaction().begin();
                em.remove(product);
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void update(Product product){
        EntityManager em = eFactory.createEntityManager();
        try {
              if (product != null) {
                em.getTransaction().begin();
                em.merge(product);
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
