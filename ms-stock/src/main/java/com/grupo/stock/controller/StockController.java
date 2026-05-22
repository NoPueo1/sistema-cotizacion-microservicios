package com.grupo.stock.controller;
import com.grupo.stock.model.Stock;
import com.grupo.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/stock") @RequiredArgsConstructor
public class StockController {
    private final StockService service;
    @GetMapping("/{equipoId}") public Stock verStock(@PathVariable Long equipoId) { return service.obtenerPorEquipo(equipoId); }
    @PutMapping("/{equipoId}") public Stock moverStock(@PathVariable Long equipoId, @RequestParam Integer cantidad) { return service.actualizar(equipoId, cantidad); }
}