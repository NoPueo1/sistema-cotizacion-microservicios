package com.grupo.cliente.dto;
import lombok.*;
@Data @AllArgsConstructor
public class ClienteResponseDTO {
    private Long id;
    private String nombre;
    private String documentoIdentidad;
    private String correo;
    private String telefono;
}