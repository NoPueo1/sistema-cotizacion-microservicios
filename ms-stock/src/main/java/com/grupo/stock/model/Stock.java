package com.grupo.stock.model;
import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Entity @Table(name = "stock")
public class Stock {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true) private Long equipoId;
    @Column(nullable = false) private Integer cantidadDisponible;
}