package com.grupo.notificacion.service;

import com.grupo.notificacion.dto.NotificacionRequestDTO;
import com.grupo.notificacion.model.Notificacion;
import com.grupo.notificacion.repository.NotificacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificacionServiceTest {

    @Mock
    private NotificacionRepository repo;

    @InjectMocks
    private NotificacionService notificacionService;

    private Notificacion notificacionSimulada;
    private NotificacionRequestDTO requestDto;

    @BeforeEach
    void setUp() {
        requestDto = new NotificacionRequestDTO("cliente@mail.com", "Su cotizacion ha sido aprobada");
        notificacionSimulada = new Notificacion(1L, "cliente@mail.com", "Su cotizacion ha sido aprobada", LocalDateTime.now());
    }

    @Test
    void enviar_Exitoso() {
        when(repo.save(any(Notificacion.class))).thenReturn(notificacionSimulada);

        Notificacion resultado = notificacionService.enviar(requestDto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("cliente@mail.com", resultado.getCorreo());
        assertEquals("Su cotizacion ha sido aprobada", resultado.getMensaje());
        verify(repo, times(1)).save(any(Notificacion.class));
    }
}