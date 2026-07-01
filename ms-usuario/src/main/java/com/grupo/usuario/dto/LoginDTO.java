package com.grupo.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.AllArgsConstructor; // 👈 Asegúrate de tener este import
import lombok.NoArgsConstructor;  // 👈 Asegúrate de tener este import

@Data
@AllArgsConstructor // 👈 Esto genera el constructor con argumentos que usa el test
@NoArgsConstructor  // 👈 Esto genera el constructor vacío que usa Spring
public class LoginDTO {
    
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
}