package ru.geekbrains;

import ru.geekbrains.model.Product;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.model.Product;

import javax.persistence.EntityManagerFactory;;import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Получаем фабрику менеджеров сущностей
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        ProductRepository pr = new ProductRepository(factory);
        List<Product> productList = pr.findAll();

        for (Product p : productList) {
            System.out.println(p);
        }

        Optional<Product> oProduct = pr.findById(3L);
        System.out.println(oProduct);

//        pr.delete(5);

        Product product = productList.get(7);
        product.setTitle("Pineapple");
        product.setCost(2250L);
        pr.update(product);

    }
}
