package com.grupo.facturacion.repository;
import com.grupo.facturacion.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    boolean existsBySerieAndNumero(String serie, String numero);
}