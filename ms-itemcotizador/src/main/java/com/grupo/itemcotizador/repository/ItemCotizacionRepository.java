package com.grupo.itemcotizador.repository;
import com.grupo.itemcotizador.model.ItemCotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ItemCotizacionRepository extends JpaRepository<ItemCotizacion, Long> {
    List<ItemCotizacion> findByCotizacionId(Long cotizacionId);
}