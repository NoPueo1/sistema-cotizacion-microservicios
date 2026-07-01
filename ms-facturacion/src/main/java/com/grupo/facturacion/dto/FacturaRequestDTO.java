package com.grupo.facturacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaRequestDTO {
    private String serie;
    private String numero;
    private Double total;
    private Long clienteId;
    private Long cotizacionId;
}