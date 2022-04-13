package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class MainAddProduct {
    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = factory.createEntityManager();
        addProducts(em, new Product("Компьютер HP D600", 45000));
        addProducts(em, new Product("Компьютер HP D600", 45000));
        addProducts(em, new Product("Компьютер HP D600", 45000));
        addProducts(em, new Product("Компьютер HP D600", 45000));
        addProducts(em, new Product("Монитор LG G2100", 12300));
        addProducts(em, new Product("Монитор LG G2100", 12300));
        addProducts(em, new Product("Монитор LG G2100", 12300));
        addProducts(em, new Product("Монитор LG G2100", 12300));
        addProducts(em, new Product("Ноутбук Dell Vostro 650", 87950));
        addProducts(em, new Product("Ноутбук Dell Vostro 650", 87950));
        addProducts(em, new Product("Ноутбук Dell Vostro 650", 87950));
        addProducts(em, new Product("Телевизор Sumsung DS5600D", 76000));
        em.close();
    }

    public static void addProducts(EntityManager em, Product product){
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }
}
