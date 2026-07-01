package com.grupo.facturacion.service;

import com.grupo.facturacion.client.ClienteClient;
import com.grupo.facturacion.dto.ClienteDTO;
import com.grupo.facturacion.dto.FacturaRequestDTO;
import com.grupo.facturacion.dto.FacturaResponseDTO;
import com.grupo.facturacion.model.Factura;
import com.grupo.facturacion.repository.FacturaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FacturaServiceTest {

    @Mock
    private FacturaRepository repo;

    @Mock
    private ClienteClient clienteClient;

    @InjectMocks
    private FacturaService facturaService;

    private Factura facturaSimulada;
    private FacturaRequestDTO requestDto;
    private ClienteDTO clienteDtoSimulado;

    @BeforeEach
    void setUp() {
        requestDto = new FacturaRequestDTO("F001", "000023", 1500.00, 10L, 5L);
        facturaSimulada = new Factura(1L, "F001", "000023", LocalDateTime.now(), 1500.00, 10L, 5L);
        clienteDtoSimulado = new ClienteDTO(10L, "Juan Perez", "12345678", "juan@mail.com", "999888777");
    }

    @Test
    void listarTodas_Exitoso() {
        when(repo.findAll()).thenReturn(List.of(facturaSimulada));
        when(clienteClient.obtenerCliente(10L)).thenReturn(clienteDtoSimulado);

        List<FacturaResponseDTO> resultado = facturaService.listarTodas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("F001-000023", resultado.get(0).getNumeroFactura());
        assertEquals("Juan Perez", resultado.get(0).getClienteNombre());
    }

    @Test
    void emitir_Exitoso() {
        when(repo.existsBySerieAndNumero("F001", "000023")).thenReturn(false);
        when(repo.save(any(Factura.class))).thenReturn(facturaSimulada);
        when(clienteClient.obtenerCliente(10L)).thenReturn(clienteDtoSimulado);

        FacturaResponseDTO resultado = facturaService.emitir(requestDto);

        assertNotNull(resultado);
        assertEquals("F001-000023", resultado.getNumeroFactura());
        assertEquals("Juan Perez", resultado.getClienteNombre());
        verify(repo, times(1)).save(any(Factura.class));
    }

    @Test
    void emitir_Error_FacturaYaExiste() {
        when(repo.existsBySerieAndNumero("F001", "000023")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> facturaService.emitir(requestDto));
        verify(repo, never()).save(any(Factura.class));
    }

    @Test
    void mapToDTO_ManejoExcepcionClienteFeign() {
        when(repo.findAll()).thenReturn(List.of(facturaSimulada));
        // Simulamos que el cliente externo falla o lanza una excepción
        when(clienteClient.obtenerCliente(10L)).thenThrow(new RuntimeException("Error de conexión"));

        List<FacturaResponseDTO> resultado = facturaService.listarTodas();

        assertNotNull(resultado);
        assertEquals("Desconocido", resultado.get(0).getClienteNombre()); // Cae en el bloque catch limpiamente
    }
}