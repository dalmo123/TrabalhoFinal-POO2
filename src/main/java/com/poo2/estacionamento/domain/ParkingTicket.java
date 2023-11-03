    package com.poo2.estacionamento.domain;

    import jakarta.persistence.*;
    import lombok.Data;

    import java.time.LocalDateTime;
    import java.util.Optional;

    @Entity
    @Data
    public class ParkingTicket {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne
        private Vehicle vehicle;

        @ManyToOne
        private ParkingLot parkingLot;

        private LocalDateTime checkInTime;
        private LocalDateTime checkOutTime;

        private boolean isPaid = false;

    }
