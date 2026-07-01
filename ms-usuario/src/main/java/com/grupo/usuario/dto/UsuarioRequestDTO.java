package com.grupo.usuario.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.AllArgsConstructor; // 👈 Agregar este import
import lombok.NoArgsConstructor;  // 👈 Agregar este import

@Data
@AllArgsConstructor // 👈 Genera el constructor con argumentos para el test
@NoArgsConstructor  // 👈 Genera el constructor vacío (necesario para Spring)
public class UsuarioRequestDTO {
    @NotBlank private String username;
    @NotBlank private String password;
    @NotBlank private String rol;
}