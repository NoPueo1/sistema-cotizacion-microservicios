package com.grupo.cotizador.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Data @AllArgsConstructor public class CotizacionResponseDTO {
    private Long id;
    private Long clienteId;
    private String clienteNombre;
    private LocalDateTime fecha;
    private BigDecimal total;
}