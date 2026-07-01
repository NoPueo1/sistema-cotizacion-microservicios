package com.grupo.usuario.controller;
import com.grupo.usuario.dto.*;
import com.grupo.usuario.model.Usuario;
import com.grupo.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/usuarios") @RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;
    @PostMapping("/registro") public Usuario registrar(@Valid @RequestBody UsuarioRequestDTO dto) { return service.registrar(dto); }
    @PostMapping("/login") public String login(@Valid @RequestBody LoginDTO dto) { return service.login(dto); }
}