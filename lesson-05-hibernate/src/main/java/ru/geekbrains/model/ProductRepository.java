package ru.geekbrains.model;

import org.springframework.stereotype.Repository;
import ru.geekbrains.model.Customer;
import ru.geekbrains.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

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
        return executeForEntityManager(
                em -> em.createNamedQuery("findAll", Product.class).getResultList()
        );
    }

    public void delete(long id) {
        executeInTransaction(em -> {
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(product);
            }
        });
    }

    public void update(Product product){
        executeInTransaction(em -> em.merge(product));
    }

    public void insert(Product product) {
        executeInTransaction(em -> em.persist(product));
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = eFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = eFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
