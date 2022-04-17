package ru.geekbrains.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(name = "findByName", query = "select c from Customer c where c.name = :name"),
        @NamedQuery(name = "listCustomers", query = "select c from Customer c")
})
public class Customer {

    @Id
    @GeneratedValue
    @Column(name ="id")
    private long id;


    @Column(name ="name", nullable = false, length = 255)
    private String name;

    @OneToMany(
            mappedBy = "customer",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Order> orders;

    public Customer() {
    }

    public Customer(long id, String name, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.orders = orders;
    }

    public Customer(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Сustomer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
