package com.grupo.equipo.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Entity @Table(name = "equipos")
public class Equipo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(unique = true, nullable = false)
    private String codigoSku;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;
    @Column(name = "categoria_id", nullable = false)
    private Long categoriaId;
}