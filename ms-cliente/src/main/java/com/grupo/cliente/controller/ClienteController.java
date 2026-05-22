package com.grupo.cliente.controller;
import com.grupo.cliente.dto.*;
import com.grupo.cliente.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/clientes") @RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;
    @GetMapping
    public List<ClienteResponseDTO> listar() { return service.listarTodos(); }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> porId(@PathVariable Long id) { return ResponseEntity.ok(service.listarPorId(id)); }
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crear(@Valid @RequestBody ClienteRequestDTO dto) { return ResponseEntity.status(201).body(service.registrar(dto)); }
}