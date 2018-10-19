package com.company;

public class Product {
    private int price, quantity;
    private String name;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product(int price, int quantity, String name) {
        this.price = price;
        this.quantity = quantity;
        this.name = name;
    }
}
