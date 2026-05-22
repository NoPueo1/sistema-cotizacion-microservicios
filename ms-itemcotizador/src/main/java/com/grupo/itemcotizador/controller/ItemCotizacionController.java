package com.grupo.itemcotizador.controller;
import com.grupo.itemcotizador.dto.ItemRequestDTO;
import com.grupo.itemcotizador.model.ItemCotizacion;
import com.grupo.itemcotizador.service.ItemCotizacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/items") @RequiredArgsConstructor
public class ItemCotizacionController {
    private final ItemCotizacionService service;
    @GetMapping("/cotizacion/{id}") public List<ItemCotizacion> listar(@PathVariable Long id) { return service.porCotizacion(id); }
    @PostMapping public ItemCotizacion agregar(@Valid @RequestBody ItemRequestDTO dto) { return service.agregar(dto); }
}