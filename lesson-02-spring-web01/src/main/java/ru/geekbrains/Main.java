package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.persist.Product;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = context.getBean("userService", ProductService.class);

        CartService cartService = null;

        Scanner sc = new Scanner(System.in);
        for (; ; ) {

            System.out.print("Enter command: ");
            String command = sc.nextLine();
            if (command.startsWith("/")) {
                if (command.equals("/new_product")) {
                    System.out.print("Enter product title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter product cost: ");
                    Long cost = sc.nextLong();
                    productService.insert(new Product(title, cost));
                    System.out.println("New product added. Now " + productService.getCount() + " products in repository");
                } else if (command.equals("/del_product")) {
                    System.out.print("Enter product id fot delete: ");
                    Long id = sc.nextLong();
                    productService.delete(id);
                    System.out.println("New product added. Now " + productService.getCount() + " products in repository");
                } else if(command.equals("/new_cart")){
                    cartService = context.getBean(CartService.class);
                    System.out.println("Cart created");
                } else if(command.equals("/view_cart")){
                    if (cartService == null) {
                        System.out.println("Please create a new Cart");
                    }
                    cartService.getAll().forEach(System.out::println);
                } else if(command.equals("/add_cart")){
                    System.out.print("Enter id: ");
                    long id = sc.nextLong();
                    System.out.print("Enter count: ");
                    int count = sc.nextInt();
                    cartService.insertProduct(id, count);
                }else if(command.equals("/end")){
                    return;
                }

            } else {

            }


        }
    }
}
