package ru.geekbrains.persist;

public class Product {
    private long id;
    private String title;
    private long cost;

    public Product(int id, String title, long cost) {
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
}
