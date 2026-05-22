package com.grupo.categoria.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo.categoria.dto.CategoriaRequestDTO;
import com.grupo.categoria.dto.CategoriaResponseDTO;
import com.grupo.categoria.service.CategoriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController @RequestMapping("/api/categorias") @RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService service;
    @GetMapping
    public List<CategoriaResponseDTO> listarTodos() { return service.listarTodos(); }
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> listarPorId(@PathVariable Long id) { return ResponseEntity.ok(service.listarPorId(id)); }
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> registrar(@Valid @RequestBody CategoriaRequestDTO dto) { return ResponseEntity.status(201).body(service.registrar(dto)); }
}