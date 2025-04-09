package com.aston.intensive.simplespringbootapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

/**
 * Entity representing a physical address.
 * <p>
 * Stores basic location information such as building, street, city, region,
 * and optional geolocation coordinates (latitude and longitude).
 * <p>
 * Each address may be associated with multiple {@link Attraction} entities.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "address")
public class Address {

    /** Unique identifier for the address. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private UUID id;

    /** Building number (e.g. 10, 221B). */
    private Integer building;

    /** Street name (e.g. Baker Street). */
    private String street;

    /** City where the address is located. */
    private String city;

    /** Region or state of the address. */
    private String region;

    /** Geographic latitude coordinate. */
    private Double latitude;

    /** Geographic longitude coordinate. */
    private Double longitude;

    /**
     * Attractions associated with this address.
     * One address can be linked to multiple attractions.
     */
    @OneToMany(mappedBy = "address")
    private Set<Attraction> attractions;

}
