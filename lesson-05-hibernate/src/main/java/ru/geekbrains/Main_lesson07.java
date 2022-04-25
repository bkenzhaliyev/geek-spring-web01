package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.model.Product;
import ru.geekbrains.model.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class Main_lesson07 {
    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        EntityManager em = factory.createEntityManager();

        ProductRepository pr = new ProductRepository(factory);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.like(root.get("title"), "%HP%"));

        List<Product> products = em.createQuery(query
                .select(root)
                .where(predicates.toArray(new Predicate[0]))).getResultList();


        for (Product p : products) {
            System.out.println(p);
        }
    }
}
