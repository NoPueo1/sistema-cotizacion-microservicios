package com.grupo.notificacion.controller;
import com.grupo.notificacion.dto.NotificacionRequestDTO;
import com.grupo.notificacion.model.Notificacion;
import com.grupo.notificacion.service.NotificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/notificaciones") @RequiredArgsConstructor
public class NotificacionController {
    private final NotificacionService service;
    @PostMapping public Notificacion enviar(@Valid @RequestBody NotificacionRequestDTO dto) { return service.enviar(dto); }
}