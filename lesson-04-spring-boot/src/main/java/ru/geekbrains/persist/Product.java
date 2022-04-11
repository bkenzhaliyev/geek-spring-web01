package ru.geekbrains.persist;

public class Product {
    private Long id;
    private String title;
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
}
