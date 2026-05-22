package com.grupo.itemcotizador.model;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Entity @Table(name = "items_cotizacion")
public class ItemCotizacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private Long cotizacionId;
    @Column(nullable = false) private Long equipoId;
    @Column(nullable = false) private Integer cantidad;
    @Column(nullable = false, precision = 12, scale = 2) private BigDecimal precioUnitario;
}