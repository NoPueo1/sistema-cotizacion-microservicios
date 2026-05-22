package com.grupo.notificacion.service;
import com.grupo.notificacion.dto.NotificacionRequestDTO;
import com.grupo.notificacion.model.Notificacion;
import com.grupo.notificacion.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class NotificacionService {
    private final NotificacionRepository repo;
    public Notificacion enviar(NotificacionRequestDTO dto) {
        System.out.println("SIMULANDO ENVÍO DE CORREO A: " + dto.getCorreo());
        return repo.save(new Notificacion(null, dto.getCorreo(), dto.getMensaje(), null));
    }
}