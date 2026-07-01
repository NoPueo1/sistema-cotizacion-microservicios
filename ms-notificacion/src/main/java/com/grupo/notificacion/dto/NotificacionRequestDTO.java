package com.grupo.notificacion.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionRequestDTO {
    @NotBlank @Email private String correo;
    @NotBlank private String mensaje;
}