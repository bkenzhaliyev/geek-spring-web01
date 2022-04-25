package ru.geekbrains.model;

import javax.persistence.*;

@Entity
@Table(name = "orders")
//@NamedQueries({
//        @NamedQuery(name = "findByName", query = "from Сustomer c where c.name =:сustomer"),
//        @NamedQuery(name = "findAll", query = "from Сustomer")
//})
public class Order {

    @Id
    @GeneratedValue
    @Column(name ="id")
    private long id;


    @ManyToOne
    private Product product;

    @ManyToOne
    private Customer customer;

    @Column(name="cost")
    private long cost;

    @Column(name="quantity", nullable = false)
    private Integer quantity;

    public Order() {

    }

    public Order(long id, Product product, Customer customer, long cost, Integer quantity) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.cost = cost;
        this.quantity = quantity;
    }

    public Order(Product product, Customer customer, long cost, Integer quantity) {
        this.product = product;
        this.customer = customer;
        this.cost = cost;
        this.quantity = quantity;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public long getCost() {
        return cost;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
