package com.grupo.cotizador.service;

import com.grupo.cotizador.client.ClienteClient;
import com.grupo.cotizador.dto.ClienteDTO;
import com.grupo.cotizador.dto.CotizacionRequestDTO;
import com.grupo.cotizador.dto.CotizacionResponseDTO;
import com.grupo.cotizador.model.Cotizacion;
import com.grupo.cotizador.repository.CotizacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CotizacionServiceTest {

    @Mock
    private CotizacionRepository repo;

    @Mock
    private ClienteClient clienteClient;

    @InjectMocks
    private CotizacionService cotizacionService;

    private Cotizacion cotizacionSimulada;
    private CotizacionRequestDTO requestDto;
    private ClienteDTO clienteDto;

    @BeforeEach
    void setUp() {
        requestDto = new CotizacionRequestDTO(1L, 1500.0);
        cotizacionSimulada = new Cotizacion(1L, 1L, null, 1500.0);
        clienteDto = new ClienteDTO(1L, "Carlos Perez");
    }

    @Test
    void listar_Exitoso() {
        when(repo.findAll()).thenReturn(List.of(cotizacionSimulada));
        when(clienteClient.obtenerCliente(1L)).thenReturn(clienteDto);

        List<CotizacionResponseDTO> resultado = cotizacionService.listar();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Carlos Perez", resultado.get(0).getClienteNombre());
    }

    @Test
    void crear_Exitoso() {
        when(repo.save(any())).thenReturn(cotizacionSimulada);
        when(clienteClient.obtenerCliente(1L)).thenReturn(clienteDto);

        CotizacionResponseDTO resultado = cotizacionService.crear(requestDto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Carlos Perez", resultado.getClienteNombre());
        verify(repo, times(1)).save(any());
    }

    @Test
    void crear_Exitoso_CuandoClienteFalla() {
        when(repo.save(any())).thenReturn(cotizacionSimulada);
        when(clienteClient.obtenerCliente(1L)).thenThrow(new RuntimeException());

        CotizacionResponseDTO resultado = cotizacionService.crear(requestDto);

        assertNotNull(resultado);
        assertEquals("Desconocido", resultado.getClienteNombre());
    }
}