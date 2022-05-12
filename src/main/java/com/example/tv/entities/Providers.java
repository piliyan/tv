package com.example.tv.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "providers")
public class Providers {
    @Id
    @SequenceGenerator(
            name="providers_sequence",
            sequenceName = "providers_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "providers_sequence"
    )
    private Integer ID;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private LocalDate signedDate;
    @Column(nullable = false)
    private LocalDate durationDate;

    @OneToMany(mappedBy = "provider")
    private List<TVChannels> tvChannels;

    public Providers() {
    }

    public Providers(String name, LocalDate signedDate, LocalDate durationDate) {
        this.name = name;
        this.signedDate = signedDate;
        this.durationDate = durationDate;
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
        return "Providers{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", signedDate=" + signedDate +
                ", durationDate=" + durationDate +
                '}';
    }
}
