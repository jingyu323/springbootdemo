package com.rain.es.test;

public class Product {


    public Product(long id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }


    public Product() {

    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private long id;

    public Product(String name, String title, double price) {
        this.name = name;
        this.title = title;
        this.price = price;
    }

    private String title;
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
