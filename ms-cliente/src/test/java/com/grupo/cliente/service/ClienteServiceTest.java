package com.grupo.cliente.service;

import com.grupo.cliente.dto.ClienteRequestDTO;
import com.grupo.cliente.dto.ClienteResponseDTO;
import com.grupo.cliente.model.Cliente;
import com.grupo.cliente.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository repo;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente clienteSimulado;
    private ClienteRequestDTO requestDto;

    @BeforeEach
    void setUp() {
        requestDto = new ClienteRequestDTO("Juan Perez", "12345678", "juan@mail.com", "999888777");
        clienteSimulado = new Cliente(1L, "Juan Perez", "12345678", "juan@mail.com", "999888777");
    }

    @Test
    void listarTodos_Exitoso() {
        when(repo.findAll()).thenReturn(List.of(clienteSimulado));

        List<ClienteResponseDTO> resultado = clienteService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Juan Perez", resultado.get(0).getNombre());
    }

    @Test
    void listarPorId_Exitoso() {
        when(repo.findById(1L)).thenReturn(Optional.of(clienteSimulado));

        ClienteResponseDTO resultado = clienteService.listarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Juan Perez", resultado.getNombre());
    }

    @Test
    void listarPorId_Error_NoEncontrado() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clienteService.listarPorId(1L));
    }

    @Test
    void registrar_Exitoso() {
        when(repo.existsByDocumentoIdentidad("12345678")).thenReturn(false);
        when(repo.save(any(Cliente.class))).thenReturn(clienteSimulado);

        ClienteResponseDTO resultado = clienteService.registrar(requestDto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("12345678", resultado.getDocumentoIdentidad());
        verify(repo, times(1)).save(any(Cliente.class));
    }

    @Test
    void registrar_Error_DocumentoExiste() {
        when(repo.existsByDocumentoIdentidad("12345678")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> clienteService.registrar(requestDto));
        verify(repo, never()).save(any(Cliente.class));
    }
}