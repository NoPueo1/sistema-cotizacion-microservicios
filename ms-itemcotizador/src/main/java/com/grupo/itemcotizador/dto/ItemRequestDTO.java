package com.grupo.itemcotizador.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import lombok.Data;

@Data public class ItemRequestDTO {
    @NotNull private Long cotizacionId;
    @NotNull private Long equipoId;
    @NotNull @Positive private Integer cantidad;
    @NotNull @Positive private BigDecimal precioUnitario;
}