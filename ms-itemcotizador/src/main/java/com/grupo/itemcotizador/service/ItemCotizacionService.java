package com.grupo.itemcotizador.service;
import com.grupo.itemcotizador.dto.ItemRequestDTO;
import com.grupo.itemcotizador.model.ItemCotizacion;
import com.grupo.itemcotizador.repository.ItemCotizacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @RequiredArgsConstructor
public class ItemCotizacionService {
    private final ItemCotizacionRepository repo;
    public List<ItemCotizacion> porCotizacion(Long cotizacionId) { return repo.findByCotizacionId(cotizacionId); }
    public ItemCotizacion agregar(ItemRequestDTO dto) { return repo.save(new ItemCotizacion(null, dto.getCotizacionId(), dto.getEquipoId(), dto.getCantidad(), dto.getPrecioUnitario())); }
}