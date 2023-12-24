package com.amadeus.flightsearchapi.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "airports")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String city;

    private Airport(){}

    public Airport(Long id, String city) {
        this.id = id;
        this.city = city;
    }
}
