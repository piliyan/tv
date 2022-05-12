package com.example.tv.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tvPackages")
public class TVPackages {
    @Id
    @SequenceGenerator(
            name="tvPackages_sequence",
            sequenceName = "tvPackages_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tvPackages_sequence"
    )
    private Integer ID;
    @Column(nullable = false, unique = true)
    private String category;
    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "tvPackage")
    private List<TVChannels> tvChannels;

    @OneToMany(mappedBy = "tvPackage")
    private List<TVPackageContracts> tvPackageContracts;

    public TVPackages() {
    }

    public TVPackages(String category, Double price) {
        this.category = category;
        this.price = price;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TVPackages{" +
                "ID=" + ID +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
