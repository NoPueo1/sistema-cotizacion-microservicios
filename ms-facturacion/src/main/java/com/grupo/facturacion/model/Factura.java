package com.grupo.facturacion.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Entity @Table(name = "facturas")
public class Factura {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 4)
    private String serie;
    @Column(nullable = false, length = 10)
    private String numero;
    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal total;
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
    @Column(name = "cotizacion_id", nullable = false)
    private Long cotizacionId;
    @PrePersist protected void onCreate() { this.fechaEmision = LocalDateTime.now(); }
}