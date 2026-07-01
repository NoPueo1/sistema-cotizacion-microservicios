package com.grupo.equipo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoResponseDTO {
    private Long id;
    private String nombre;
    private String codigoSku;
    private Double precio;
    private String categoriaNombre;
}