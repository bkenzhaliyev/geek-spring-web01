package ru.geekbrains.persist;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository2 {

    @PersistenceContext
    EntityManager em;


    public List<Product> findAll() {
        return em.createQuery("from Product", Product.class).getResultList();
    }


    @Override
    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        em.createQuery("delete from Product where id= :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public Product save(Product product) {
        if (product.getId() == null) {
            System.out.println("new product id = " + product.getId());
            em.persist(product);
        } else {
            em.merge(product);
        }
        return product;
    }

    @Override
    public long getCount() {
        return 0;
    }
}
