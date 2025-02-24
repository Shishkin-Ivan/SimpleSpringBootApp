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
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "service_id")
    private UUID id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ServiceType type;

    @ManyToMany(mappedBy  = "services")
    private Set<Attraction> attractions;
}