package com.grupo.cotizador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionResponseDTO {
    private Long id;
    private Long clienteId;
    private String clienteNombre;
    private LocalDateTime fecha;
    private Double total;
}