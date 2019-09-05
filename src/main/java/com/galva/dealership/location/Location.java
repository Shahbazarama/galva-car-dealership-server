package com.galva.dealership.location;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "locations")
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    public Location() {}

    public Location(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
