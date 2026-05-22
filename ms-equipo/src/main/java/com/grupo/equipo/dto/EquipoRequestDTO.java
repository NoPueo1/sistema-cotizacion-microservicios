package com.grupo.equipo.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import lombok.Data;
@Data
public class EquipoRequestDTO {
    @NotBlank private String nombre;
    @NotBlank private String codigoSku;
    @NotNull @Positive private BigDecimal precio;
    @NotNull private Long categoriaId;
}