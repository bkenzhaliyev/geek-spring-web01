package ru.geekbrains.dto;

import javax.persistence.*;
//import javax.validation.contraints.NotBlank;

public class ProductDto {

    private long id;

    private String title;

    private long cost;

    public ProductDto() {
    }

    public ProductDto(long id, String title, long cost) {
        this.id = id;
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
