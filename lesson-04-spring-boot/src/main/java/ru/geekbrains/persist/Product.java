package ru.geekbrains.persist;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private Long cost;

    public Product() {
    }

    public Product(Long id, String title, Long cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getCost() {
        return cost;
    }

}
