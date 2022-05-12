package com.example.tv.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tvPackageContracts")
public class TVPackageContracts {
    @Id
    @SequenceGenerator(
            name="tvPackageContracts_sequence",
            sequenceName = "tvPackageContracts_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tvPackageContracts_sequence"
    )
    private Integer ID;
    @ManyToOne
    @JoinColumn(name = "clientID")
    private Clients client;
    @ManyToOne
    @JoinColumn(name = "tvPackageID")
    private TVPackages tvPackage;
    @Column(nullable = false)
    private LocalDate signedDate;
    @Column(nullable = false)
    private LocalDate durationDate;

    public TVPackageContracts() {

    }

    public TVPackageContracts(Clients client, TVPackages tvPackage, LocalDate signedDate, LocalDate durationDate) {
        this.client = client;
        this.tvPackage = tvPackage;
        this.signedDate = signedDate;
        this.durationDate = durationDate;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public TVPackages getTvPackage() {
        return tvPackage;
    }

    public void setTvPackage(TVPackages tvPackage) {
        this.tvPackage = tvPackage;
    }

    public LocalDate getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(LocalDate signedDate) {
        this.signedDate = signedDate;
    }

    public LocalDate getDurationDate() {
        return durationDate;
    }

    public void setDurationDate(LocalDate durationDate) {
        this.durationDate = durationDate;
    }

    @Override
    public String toString() {
        return "TVPackageContracts{" +
                "ID=" + ID +
                ", client=" + client +
                ", tvPackage=" + tvPackage +
                ", signedDate=" + signedDate +
                ", durationDate=" + durationDate +
                '}';
    }
}