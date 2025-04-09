package com.aston.intensive.simplespringbootapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

/**
 * Entity representing a service that can be provided at one or more attractions.
 * <p>
 * Examples of services include audio guides, transportation, accessibility assistance, etc.
 * Each service has a name, description, and type (defined by {@link ServiceType}).
 * <p>
 * A service can be associated with multiple attractions, and vice versa.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "services")
public class Service {

    /** Unique identifier for the service. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "service_id")
    private UUID id;

    /** Name of the service (e.g. "Audio Guide"). */
    private String name;

    /** Detailed description of what the service provides. */
    private String description;

    /** Category/type of the service (e.g. TRANSPORT, GUIDE). */
    @Enumerated(EnumType.STRING)
    private ServiceType type;

    /**
     * Set of attractions that offer this service.
     * <p>
     * This is a bidirectional many-to-many relationship.
     */
    @ManyToMany(mappedBy  = "services")
    private Set<Attraction> attractions;

}
