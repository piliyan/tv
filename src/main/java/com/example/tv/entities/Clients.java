package com.example.tv.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
public class Clients {
    @Id
    @SequenceGenerator(
            name="clients_sequence",
            sequenceName = "clients_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "clients_sequence"
    )
    private Integer ID;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true, length = 10)
    private String egn;
    @Column(length = 10)
    private String phone;
    private String email;

    @OneToMany(mappedBy = "client")
    private List<TVChannelContracts> tvChannelContracts;

    @OneToMany(mappedBy = "client")
    private List<TVPackageContracts> tvPackageContracts;

    public Clients() {
    }

    public Clients(String name, String egn, String phone, String email) {
        this.name = name;
        this.egn = egn;
        this.phone = phone;
        this.email = email;
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

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", egn='" + egn + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
