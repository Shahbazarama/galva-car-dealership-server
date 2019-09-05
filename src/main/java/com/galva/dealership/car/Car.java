package com.galva.dealership.car;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "cars")
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String vin;

    @Column
    private int year;

    @Column
    private String make;

    @Column
    private String model;

    @Column
    private int miles;

    @Column
    private int price;

    @Column
    private String photo_url;

    @Column(name = "location_id")
    private int locationId;

    public Car() { }

    public Car(String vin, int year, String make, String model, int miles, int price, String photo_url, int locationId) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.miles = miles;
        this.price = price;
        this.photo_url = photo_url;
        this.locationId = locationId;
    }
}
