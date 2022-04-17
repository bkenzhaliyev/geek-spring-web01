package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CreateOrders {
    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = factory.createEntityManager();

        ProductRepository pr = new ProductRepository(factory);
        List<Product> productList = pr.findAll();

        CustomerRepository cr = new CustomerRepository(factory);
        List<Customer> customerList = cr.findAll();

        addCustomers(em, new Customer("Василий Теркин"));
        addCustomers(em, new Customer("Павел Судоплатов"));
        addCustomers(em, new Customer("Иван Кожедуб"));
        addCustomers(em, new Customer("Василий Зайцев"));

        addOrders(em, productList.get(1), customerList.get(1));
        addOrders(em, productList.get(2), customerList.get(2));
        addOrders(em, productList.get(3), customerList.get(3));
        addOrders(em, productList.get(4), customerList.get(1));
        addOrders(em, productList.get(5), customerList.get(2));
        addOrders(em, productList.get(6), customerList.get(3));
        addOrders(em, productList.get(1), customerList.get(1));
        addOrders(em, productList.get(2), customerList.get(2));
        addOrders(em, productList.get(7), customerList.get(3));
        addOrders(em, productList.get(8), customerList.get(1));
        addOrders(em, productList.get(9), customerList.get(2));
        addOrders(em, productList.get(10), customerList.get(3));
        em.close();
    }

    public static void addOrders(EntityManager em, Product product, Customer customer){
        Order order = new Order(product, customer, product.getCost(), 1);
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
    }

    public static void addCustomers(EntityManager em, Customer customer){
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
    }
}
