package ru.geekbrains;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("ru.geekbrains")
public class AppConfig {

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepositoryImpl();
    }

    @Bean
    public ProductService userService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

    @Bean
    @Scope("prototype")
    public CartService cartService() {
        return new CartService();
    }
}
