package com.grupo.facturacion.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import lombok.Data;
@Data
public class FacturaRequestDTO {
    @NotBlank private String serie;
    @NotBlank private String numero;
    @NotNull private Long cotizacionId;
    @NotNull private Long clienteId;
    @NotNull @Positive private BigDecimal total;
}