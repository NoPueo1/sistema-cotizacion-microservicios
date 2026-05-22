package com.grupo.facturacion.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;
@Data @AllArgsConstructor
public class FacturaResponseDTO {
    private Long id;
    private String comprobanteCompleto;
    private LocalDateTime fechaEmision;
    private BigDecimal total;
    private String clienteNombre; 
}