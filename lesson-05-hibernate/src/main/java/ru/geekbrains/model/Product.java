package ru.geekbrains.model;

import javax.persistence.*;
import java.util.List;

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
    private long id;

    @Column(name ="title")
    private String title;

    @Column(name ="cost")
    private long cost;

    @OneToMany(
            mappedBy = "product",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Order> orders;

    public Product(long id, String title, long cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }


    public Product(long id, String title, long cost, List<Order> orders) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.orders = orders;
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
    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getCost() {
        return cost;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
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
