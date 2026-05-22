package com.grupo.notificacion.dto;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data public class NotificacionRequestDTO {
    @NotBlank @Email private String correo;
    @NotBlank private String mensaje;
}