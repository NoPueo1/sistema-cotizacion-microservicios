package com.grupo.cliente.dto;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data
public class ClienteRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El documento es obligatorio")
    private String documentoIdentidad;
    @Email
    private String correo;
    private String telefono;
}