package com.grupo.stock.service;
import com.grupo.stock.model.Stock;
import com.grupo.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class StockService {
    private final StockRepository repo;
    public Stock obtenerPorEquipo(Long equipoId) { return repo.findByEquipoId(equipoId).orElseThrow(() -> new RuntimeException("Stock no encontrado")); }
    public Stock actualizar(Long equipoId, Integer cantidad) {
        Stock s = repo.findByEquipoId(equipoId).orElse(new Stock(null, equipoId, 0));
        s.setCantidadDisponible(s.getCantidadDisponible() + cantidad);
        return repo.save(s);
    }
}