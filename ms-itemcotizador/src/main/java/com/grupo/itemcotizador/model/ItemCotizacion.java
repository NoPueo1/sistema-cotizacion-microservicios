package com.grupo.itemcotizador.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity                                 
@Table(name = "items_cotizacion")       
public class ItemCotizacion {
    
    @Id                                
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @NotNull(message = "El ID de la cotización es obligatorio")
    private Long cotizacionId;
    
    @NotNull(message = "El ID del equipo es obligatorio")
    private Long equipoId;
    
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad mínima permitida para un ítem es 1")
    private Integer cantidad;
    
    @NotNull(message = "El precio unitario no puede ser nulo")
    @Positive(message = "El precio unitario debe ser un número positivo mayor a cero")
    private Double precioUnitario;
}