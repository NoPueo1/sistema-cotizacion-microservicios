package com.grupo.usuario.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data public class UsuarioRequestDTO {
    @NotBlank private String username;
    @NotBlank private String password;
    @NotBlank private String rol;
}