package com.grupo.proveedor.model;
import jakarta.persistence.*;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Entity @Table(name = "proveedores")
public class Proveedor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String razonSocial;
    @Column(unique = true, nullable = false)
    private String rut;
    private String direccion;
}