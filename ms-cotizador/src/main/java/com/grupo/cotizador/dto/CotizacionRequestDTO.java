package com.grupo.cotizador.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import lombok.Data;

@Data public class CotizacionRequestDTO {
    @NotNull private Long clienteId;
    @NotNull @Positive private BigDecimal total;
}