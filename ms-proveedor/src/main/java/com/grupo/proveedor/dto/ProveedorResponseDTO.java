package com.grupo.proveedor.dto;
import lombok.*;
@Data @AllArgsConstructor
public class ProveedorResponseDTO {
    private Long id;
    private String razonSocial;
    private String rut;
    private String direccion;
}