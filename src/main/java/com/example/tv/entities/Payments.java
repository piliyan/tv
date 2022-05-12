package com.example.tv.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payments {
    @Id
    @SequenceGenerator(
            name="payments_sequence",
            sequenceName = "payments_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payments_sequence"
    )
    private Integer ID;
    @ManyToOne
    @JoinColumn(name="tvChannelContractID")
    private TVChannelContracts tvChannelContract;
    @ManyToOne
    @JoinColumn(name="tvPackageContractID")
    private TVPackageContracts tvPackageContract;
    @Column(nullable = false)
    private LocalDate date;
    @Column(columnDefinition = "default=0")
    private Double bill;

    public Payments() {
    }

    public Payments(TVChannelContracts tvChannelContract, TVPackageContracts tvPackageContract, LocalDate date, Double bill) {
        this.tvChannelContract = tvChannelContract;
        this.tvPackageContract = tvPackageContract;
        this.date = date;
        this.bill = bill;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public TVChannelContracts getTvChannelContract() {
        return tvChannelContract;
    }

    public void setTvChannelContract(TVChannelContracts tvChannelContract) {
        this.tvChannelContract = tvChannelContract;
    }

    public TVPackageContracts getTvPackageContract() {
        return tvPackageContract;
    }

    public void setTvPackageContract(TVPackageContracts tvPackageContract) {
        this.tvPackageContract = tvPackageContract;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getBill() {
        return bill;
    }

    public void setBill(Double bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "ID=" + ID +
                ", tvChannelContract=" + tvChannelContract +
                ", tvPackageContract=" + tvPackageContract +
                ", date=" + date +
                ", bill=" + bill +
                '}';
    }
}
