package com.aston.intensive.simplespringbootapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private UUID id;

    private Integer building;

    private String street;

    private String city;

    private String region;

    private Double latitude;

    private Double longitude;

    @OneToMany(mappedBy = "address")
    private Set<Attraction> attractions;
}

