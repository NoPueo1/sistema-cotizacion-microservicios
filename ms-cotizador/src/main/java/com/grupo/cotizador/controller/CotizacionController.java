package com.grupo.cotizador.controller;
import com.grupo.cotizador.dto.*;
import com.grupo.cotizador.service.CotizacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/cotizaciones") @RequiredArgsConstructor
public class CotizacionController {
    private final CotizacionService service;
    @GetMapping public List<CotizacionResponseDTO> listar() { return service.listar(); }
    @PostMapping public ResponseEntity<CotizacionResponseDTO> crear(@Valid @RequestBody CotizacionRequestDTO dto) { return ResponseEntity.status(201).body(service.crear(dto)); }
}