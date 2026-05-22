package com.grupo.cotizador.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Entity @Table(name = "cotizaciones")
public class Cotizacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private Long clienteId;
    @Column(nullable = false) private LocalDateTime fecha;
    @Column(nullable = false, precision = 12, scale = 2) private BigDecimal total;
    @PrePersist protected void onCreate() { this.fecha = LocalDateTime.now(); }
}