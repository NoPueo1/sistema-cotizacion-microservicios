package com.grupo.equipo.controller;

import com.grupo.equipo.dto.EquipoRequestDTO;
import com.grupo.equipo.dto.EquipoResponseDTO;
import com.grupo.equipo.service.EquipoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService equipoService;

    @GetMapping
    public ResponseEntity<List<EquipoResponseDTO>> listar() {
        return ResponseEntity.ok(equipoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<EquipoResponseDTO> crear(@Valid @RequestBody EquipoRequestDTO equipo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipoService.registrar(equipo));
    }
}