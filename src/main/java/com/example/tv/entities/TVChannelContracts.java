package com.example.tv.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tvChannelContracts")
public class TVChannelContracts {
    @Id
    @SequenceGenerator(
            name="tvChannelContracts_sequence",
            sequenceName = "tvChannelContracts_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tvChannelContracts_sequence"
    )
    private Integer ID;
    @ManyToOne
    @JoinColumn(name = "clientID")
    private Clients client;
    @ManyToOne
    @JoinColumn(name = "tvChannelID")
    private TVChannels tvChannel;
    @Column(nullable = false)
    private LocalDate signedDate;
    @Column(nullable = false)
    private LocalDate durationDate;

    public TVChannelContracts() {

    }

    public TVChannelContracts(Clients client, TVChannels tvChannel, LocalDate signedDate, LocalDate durationDate) {
        this.client = client;
        this.tvChannel = tvChannel;
        this.signedDate = signedDate;
        this.durationDate = durationDate;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public TVChannels getTvChannel() {
        return tvChannel;
    }

    public void setTvChannel(TVChannels tvChannel) {
        this.tvChannel = tvChannel;
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
        return "TVChannelContracts{" +
                "ID=" + ID +
                ", client=" + client +
                ", tvChannel=" + tvChannel +
                ", signedDate=" + signedDate +
                ", durationDate=" + durationDate +
                '}';
    }
}
