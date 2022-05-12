package com.example.tv.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tvChannels")
public class TVChannels {
    @Id
    @SequenceGenerator(
            name="tvChannels_sequence",
            sequenceName = "tvChannels_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tvChannels_sequence"
    )
    private Integer ID;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Double clientPrice;
    @Column(nullable = false)
    private Double providingPrice;
    @ManyToOne
    @JoinColumn(name = "providerId")
    private Providers provider;
    @ManyToOne
    @JoinColumn(name = "tvPackageId")
    private TVPackages tvPackage;

    @OneToMany(mappedBy = "tvChannel")
    private List<TVChannelContracts> tvChannelContracts;

    public TVChannels() {
    }

    public TVChannels(String name, Double clientPrice, Double providingPrice, Providers provider, TVPackages tvPackage) {
        this.name = name;
        this.clientPrice = clientPrice;
        this.providingPrice = providingPrice;
        this.provider = provider;
        this.tvPackage = tvPackage;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getClientPrice() {
        return clientPrice;
    }

    public void setClientPrice(Double clientPrice) {
        this.clientPrice = clientPrice;
    }

    public Double getProvidingPrice() {
        return providingPrice;
    }

    public void setProvidingPrice(Double providingPrice) {
        this.providingPrice = providingPrice;
    }

    public Providers getProvider() {
        return provider;
    }

    public void setProvider(Providers provider) {
        this.provider = provider;
    }

    public TVPackages getTvPackage() {
        return tvPackage;
    }

    public void setTvPackage(TVPackages tvPackage) {
        this.tvPackage = tvPackage;
    }

    @Override
    public String toString() {
        return "TVChannels{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", clientPrice=" + clientPrice +
                ", providingPrice=" + providingPrice +
                ", providerID=" + provider +
                ", packageID=" + tvPackage +
                '}';
    }
}
