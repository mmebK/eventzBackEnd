package com.meb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id")
    private long id;

    @Column(name = "address")
    String address;

    @Column(name = "address2")
    String address2;


    @Column(name = "city")
    String city;


    @Column(name = "country")
    String country;

    @Column(name = "region")
    String region;

    @Column(name = "zip_code")
    int zipCode;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "location")
    Event event;
}
