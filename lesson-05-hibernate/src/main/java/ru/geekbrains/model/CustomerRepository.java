package ru.geekbrains.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class CustomerRepository {
    private final EntityManagerFactory emFactory;

    public CustomerRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<Customer> findAll() {
        return executeForEntityManager(
                em -> em.createNamedQuery("listCustomers", Customer.class).getResultList()
        );
    }

    public Optional<Customer> findByName(String name) {
        return executeForEntityManager(
                em -> Optional.ofNullable(em.find(Customer.class, name))
        );
    }

    public void insert(Customer customer) {
        executeInTransaction(em -> em.persist(customer));
    }

    public void update(Customer customer) {
        executeInTransaction(em -> em.merge(customer));
    }

    public void delete(long id) {
        executeInTransaction(em -> {
            Customer customer = em.find(Customer.class, id);
            if (customer != null) {
                em.remove(customer);
            }
        });
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emFactory.createEntityManager();
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
