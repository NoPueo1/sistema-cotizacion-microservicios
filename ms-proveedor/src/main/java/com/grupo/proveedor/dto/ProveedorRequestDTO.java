package com.grupo.proveedor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorRequestDTO {
    private String razonSocial;
    private String rut;
    private String direccion;
}