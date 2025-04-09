package com.aston.intensive.simplespringbootapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

/**
 * Entity representing a tourist attraction.
 * <p>
 * Contains information such as name, description, type, location (address),
 * related services (e.g., guides, transport), and ticketing details.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "attractions")
public class Attraction {

    /** Unique identifier for the attraction. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attraction_id")
    private UUID id;

    /** Name of the attraction. */
    private String name;

    /** Brief description of the attraction. */
    private String description;

    /** Type of the attraction (e.g. MUSEUM, PARK). */
    @Enumerated(EnumType.STRING)
    private AttractionType type;

    /**
     * Address where the attraction is located.
     * <p>
     * Many attractions can share the same address.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    /**
     * Services available at the attraction.
     * <p>
     * Many-to-many relationship with services (e.g. guided tours, rentals).
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "attraction_service",
            joinColumns = @JoinColumn(name = "attraction_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<Service> services;

    /**
     * Ticket information associated with the attraction.
     * <p>
     * One-to-one relationship, unique per attraction.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_info_id", referencedColumnName = "ticket_info_id", unique = true)
    private TicketInfo ticketInfo;

}
