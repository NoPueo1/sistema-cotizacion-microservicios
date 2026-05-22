package com.grupo.proveedor.controller;
import com.grupo.proveedor.dto.*;
import com.grupo.proveedor.service.ProveedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/proveedores") @RequiredArgsConstructor
public class ProveedorController {
    private final ProveedorService service;
    @GetMapping
    public List<ProveedorResponseDTO> listar() { return service.listarTodos(); }
    @PostMapping
    public ResponseEntity<ProveedorResponseDTO> crear(@Valid @RequestBody ProveedorRequestDTO dto) { return ResponseEntity.status(201).body(service.registrar(dto)); }
}