package ru.geekbrains.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findById", query = "from Product p WHERE p.id = :id"),
        @NamedQuery(name = "findAll", query = "select u from Product u"),
        @NamedQuery(name = "delete", query = "delete from Product where id= :id")
})
public class Product {
    @Id
    @GeneratedValue
    @Column(name ="id")
    private Long id;

    @Column(name ="title")
    private String title;

    @Column(name ="cost")
    private Long cost;

    public Product(Long id, String title, Long cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Product() {
    }

    public Product(String title) {
        this.title = title;
    }

    public Product(String title, long cost) {
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
