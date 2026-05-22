package com.grupo.equipo.dto;
import java.math.BigDecimal;
import lombok.*;
@Data @AllArgsConstructor
public class EquipoResponseDTO {
    private Long id;
    private String nombre;
    private String codigoSku;
    private BigDecimal precio;
    private Long categoriaId;
    private String categoriaNombre; 
}