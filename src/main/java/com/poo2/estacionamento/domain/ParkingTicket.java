    package com.poo2.estacionamento.domain;

    import com.fasterxml.jackson.annotation.JsonInclude;
    import jakarta.persistence.*;
    import lombok.Data;

    import java.time.LocalDateTime;
    import java.util.Optional;

    @Entity
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class ParkingTicket {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private ParkingLot parkingLot;

        private LocalDateTime checkInTime;

        private LocalDateTime checkOutTime;

        private boolean isPaid = false;

    }
