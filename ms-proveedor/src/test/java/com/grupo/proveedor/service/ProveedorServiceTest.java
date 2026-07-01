package com.grupo.proveedor.service;

import com.grupo.proveedor.dto.ProveedorRequestDTO;
import com.grupo.proveedor.dto.ProveedorResponseDTO;
import com.grupo.proveedor.model.Proveedor;
import com.grupo.proveedor.repository.ProveedorRepository;
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
class ProveedorServiceTest {

    @Mock
    private ProveedorRepository repo;

    @InjectMocks
    private ProveedorService proveedorService;

    private Proveedor proveedorSimulado;
    private ProveedorRequestDTO requestDto;

    @BeforeEach
    void setUp() {
        requestDto = new ProveedorRequestDTO("Distribuidora Global S.A.", "77.777.777-7", "Av. Principal 123");
        proveedorSimulado = new Proveedor(1L, "Distribuidora Global S.A.", "77.777.777-7", "Av. Principal 123");
    }

    @Test
    void listarTodos_Exitoso() {
        when(repo.findAll()).thenReturn(List.of(proveedorSimulado));

        List<ProveedorResponseDTO> resultado = proveedorService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Distribuidora Global S.A.", resultado.get(0).getRazonSocial());
    }

    @Test
    void registrar_Exitoso() {
        when(repo.existsByRut("77.777.777-7")).thenReturn(false);
        when(repo.save(any(Proveedor.class))).thenReturn(proveedorSimulado);

        ProveedorResponseDTO resultado = proveedorService.registrar(requestDto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("77.777.777-7", resultado.getRut());
        verify(repo, times(1)).save(any(Proveedor.class));
    }

    @Test
    void registrar_Error_RutExiste() {
        when(repo.existsByRut("77.777.777-7")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> proveedorService.registrar(requestDto));
        verify(repo, never()).save(any(Proveedor.class));
    }
}