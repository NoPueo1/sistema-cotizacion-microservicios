package com.grupo.facturacion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "facturas")
public class Factura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "La serie de la factura es obligatoria")
    @Size(min = 1, max = 10, message = "La serie debe tener entre 1 y 10 caracteres")
    private String serie;
    
    @NotBlank(message = "El número de la factura es obligatorio")
    @Size(min = 1, max = 20, message = "El número de factura debe tener entre 1 y 20 caracteres")
    private String numero;
    
    @NotNull(message = "La fecha de emisión es obligatoria")
    @PastOrPresent(message = "La fecha de emisión no puede ser una fecha futura")
    private LocalDateTime fechaEmision;
    
    @NotNull(message = "El total de la factura no puede ser nulo")
    @Positive(message = "El total de la factura debe ser un número positivo mayor a cero")
    private Double total;
    
    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;
    
    @NotNull(message = "El ID de la cotización es obligatorio")
    private Long cotizacionId;
}