package com.zstyle.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double price;

    private String category;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    // ✅ EMPTY CONSTRUCTOR
    public Product() {
    }

    // ✅ ALL ARG CONSTRUCTOR
    public Product(int id, String name, double price,
                   String category, byte[] image) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    // ✅ GETTERS & SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}