package com.grupo.equipo.service;

import com.grupo.equipo.client.CategoriaClient;
import com.grupo.equipo.dto.CategoriaDTO;
import com.grupo.equipo.dto.EquipoRequestDTO;
import com.grupo.equipo.dto.EquipoResponseDTO;
import com.grupo.equipo.model.Equipo;
import com.grupo.equipo.repository.EquipoRepository;
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
class EquipoServiceTest {

    @Mock
    private EquipoRepository repo;

    @Mock
    private CategoriaClient categoriaClient; // Mockeamos también el cliente OpenFeign

    @InjectMocks
    private EquipoService equipoService;

    private Equipo equipoSimulado;
    private EquipoRequestDTO registroDto;
    private CategoriaDTO categoriaDto;

    @BeforeEach
    void setUp() {
        registroDto = new EquipoRequestDTO("Laptop Dell", "SKU-DELL-01", 1200.0, 5L);
        equipoSimulado = new Equipo(1L, "Laptop Dell", "SKU-DELL-01", 1200.0, 5L, "Descripción de prueba", 1L);
        categoriaDto = new CategoriaDTO(5L, "Tecnología");
    }

    // --- TEST LISTAR TODOS ---
    @Test
    void listarTodos_Exitoso() {
        // Arrange
        when(repo.findAll()).thenReturn(List.of(equipoSimulado));
        when(categoriaClient.obtenerCategoria(5L)).thenReturn(categoriaDto);

        // Act
        List<EquipoResponseDTO> resultado = equipoService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Tecnología", resultado.get(0).getCategoriaNombre());
    }

    // --- TESTS REGISTRAR ---
    @Test
    void registrar_Exitoso() {
        // Arrange
        when(repo.existsByCodigoSku(registroDto.getCodigoSku())).thenReturn(false);
        when(repo.save(any())).thenReturn(equipoSimulado);
        when(categoriaClient.obtenerCategoria(5L)).thenReturn(categoriaDto);

        // Act
        EquipoResponseDTO resultado = equipoService.registrar(registroDto);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("SKU-DELL-01", resultado.getCodigoSku());
        assertEquals("Tecnología", resultado.getCategoriaNombre());
        verify(repo, times(1)).save(any());
    }

    @Test
    void registrar_Error_SkuYaExiste() {
        // Arrange
        when(repo.existsByCodigoSku(registroDto.getCodigoSku())).thenReturn(true);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            equipoService.registrar(registroDto);
        });

        assertEquals("El SKU ya existe", exception.getMessage());
        verify(repo, never()).save(any());
    }

    @Test
    void registrar_Exitoso_CuandoClienteCategoriaFalla() {
        when(repo.existsByCodigoSku(registroDto.getCodigoSku())).thenReturn(false);
        when(repo.save(any())).thenReturn(equipoSimulado);
        when(categoriaClient.obtenerCategoria(5L)).thenThrow(new RuntimeException("Error de conexión Feign"));

        // Act
        EquipoResponseDTO resultado = equipoService.registrar(registroDto);

        // Assert
        assertNotNull(resultado);
        assertEquals("Desconocida", resultado.getCategoriaNombre());
    }
}