package com.grupo.categoria.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data @AllArgsConstructor
public class CategoriaResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
}