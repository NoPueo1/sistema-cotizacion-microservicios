package com.grupo.itemcotizador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDTO {
    private Long cotizacionId;
    private Long equipoId;
    private Integer cantidad;
    private Double precioUnitario;
}