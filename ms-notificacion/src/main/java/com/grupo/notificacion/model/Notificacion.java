package com.grupo.notificacion.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Entity @Table(name = "notificaciones")
public class Notificacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String correoDestino;
    @Column(nullable = false) private String mensaje;
    private LocalDateTime fechaEnvio;
    @PrePersist protected void onCreate() { this.fechaEnvio = LocalDateTime.now(); }
}