package com.aston.intensive.simplespringbootapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tickets_info")
public class TicketInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticket_info_id")
    private UUID id;

    private Double price;

    private String currency;

    private Boolean availability;

    @OneToOne(mappedBy = "ticketInfo")
    private Attraction attraction;
}