package com.grupo.cotizador.repository;
import com.grupo.cotizador.model.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CotizacionRepository extends JpaRepository<Cotizacion, Long> {}