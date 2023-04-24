package com.example.locationvoiture.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String dateReservation;
    private String dateRetour;
    @ManyToOne
    @JoinColumn(name="client_id_FK")
    private Client client;

    public Reservation() {
    }

    public Reservation(String dateReservation, String dateRetour) {
        this.dateReservation = dateReservation;
        this.dateRetour = dateRetour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }


    public String getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(String dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", dateReservation=" + dateReservation +
                ", dateRetour=" + dateRetour +
                '}';
    }
}
