package com.grupo.facturacion.controller;
import com.grupo.facturacion.dto.*;
import com.grupo.facturacion.service.FacturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/facturas") @RequiredArgsConstructor
public class FacturaController {
    private final FacturaService service;
    @GetMapping
    public List<FacturaResponseDTO> listar() { return service.listarTodas(); }
    @PostMapping
    public ResponseEntity<FacturaResponseDTO> crear(@Valid @RequestBody FacturaRequestDTO dto) { return ResponseEntity.status(201).body(service.emitir(dto)); }
}