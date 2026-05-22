package com.grupo.proveedor.dto;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data
public class ProveedorRequestDTO {
    @NotBlank private String razonSocial;
    @NotBlank private String rut;
    private String direccion;
}