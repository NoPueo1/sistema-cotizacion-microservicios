package com.grupo.facturacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaResponseDTO {
    private Long id;
    private String numeroFactura;
    private LocalDateTime fechaEmision;
    private Double total;
    private String clienteNombre;  
}