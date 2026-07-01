package com.grupo.cotizador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionRequestDTO {
    private Long clienteId;
    private Double total;
}