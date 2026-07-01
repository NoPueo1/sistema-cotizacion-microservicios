package com.grupo.categoria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity                                 // <-- 1. Agrega esto para decirle a Spring que es una tabla
@Table(name = "categorias")             // <-- (Opcional) Define el nombre de la tabla en MySQL
public class Categoria {
    
    @Id                                 // <-- 2. Define tu llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <-- 3. Hace que el ID sea autoincremental
    private Long id;
    
    @NotBlank(message = "El nombre de la categoría no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;
    
    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String descripcion;
}