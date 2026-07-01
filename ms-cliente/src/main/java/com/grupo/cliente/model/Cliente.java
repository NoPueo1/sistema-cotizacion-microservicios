package com.grupo.cliente.model;
import jakarta.persistence.*;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Entity @Table(name = "clientes")
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(name = "documento_identidad", unique = true, nullable = false)
    private String documentoIdentidad;
    private String correo;
    private String telefono;
}