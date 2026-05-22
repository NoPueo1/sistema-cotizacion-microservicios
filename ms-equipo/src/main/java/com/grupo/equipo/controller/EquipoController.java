package com.grupo.equipo.controller;
import com.grupo.equipo.dto.*;
import com.grupo.equipo.service.EquipoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/equipos") @RequiredArgsConstructor
public class EquipoController {
    private final EquipoService service;
    @GetMapping
    public List<EquipoResponseDTO> listar() { return service.listarTodos(); }
    @PostMapping
    public ResponseEntity<EquipoResponseDTO> crear(@Valid @RequestBody EquipoRequestDTO dto) { return ResponseEntity.status(201).body(service.registrar(dto)); }
}