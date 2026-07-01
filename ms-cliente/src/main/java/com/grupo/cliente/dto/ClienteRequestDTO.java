package com.grupo.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO {
    private String nombre;
    private String documentoIdentidad;
    private String correo;
    private String telefono;
}