package com.aston.intensive.simplespringbootapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 * Entity representing ticket-related information for an attraction.
 * <p>
 * Contains details such as price, currency, and availability.
 * Each {@link TicketInfo} is associated with a single {@link Attraction}.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tickets_info")
public class TicketInfo {

    /** Unique identifier for the ticket info record. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticket_info_id")
    private UUID id;

    /** Ticket price for the attraction. */
    private Double price;

    /** Currency of the ticket price (e.g. USD, EUR). */
    private String currency;

    /** Whether tickets are currently available. */
    private Boolean availability;

    /**
     * The attraction associated with this ticket info.
     * <p>
     * This is a one-to-one bidirectional relationship.
     */
    @OneToOne(mappedBy = "ticketInfo")
    private Attraction attraction;

}
