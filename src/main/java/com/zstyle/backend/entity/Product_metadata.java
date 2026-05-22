package com.zstyle.backend.entity;


import com.zstyle.backend.enums.Gender;
import com.zstyle.backend.enums.Material;
import com.zstyle.backend.enums.Season;
import com.zstyle.backend.enums.Size;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Product_metadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String color;

    @Enumerated(EnumType.STRING)
    private Material material;

    @Enumerated(EnumType.STRING)
    private Size size;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String occasion;

    @Enumerated(EnumType.STRING)
    private Season season;

    private String style;

    private String fit;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFit() {
        return fit;
    }

    public void setFit(String fit) {
        this.fit = fit;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Product_metadata(String color) {
        this.color = color;
    }

    public Product_metadata(String color, String fit, Gender gender, int id, Material material, String occasion, Product product, Season season, Size size, String style) {
        this.color = color;
        this.fit = fit;
        this.gender = gender;
        this.id = id;
        this.material = material;
        this.occasion = occasion;
        this.product = product;
        this.season = season;
        this.size = size;
        this.style = style;
    }
}
