package com.grupo.categoria.service;

import com.grupo.categoria.dto.CategoriaRequestDTO;
import com.grupo.categoria.dto.CategoriaResponseDTO;
import com.grupo.categoria.model.Categoria;
import com.grupo.categoria.repository.CategoriaRepository;
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
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository repo;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoriaSimulada;
    private CategoriaRequestDTO requestDto;

    @BeforeEach
    void setUp() {
        requestDto = new CategoriaRequestDTO("Tecnologia", "Dispositivos electronicos");
        categoriaSimulada = new Categoria(1L, "Tecnologia", "Dispositivos electronicos");
    }

    @Test
    void listarTodos_Exitoso() {
        when(repo.findAll()).thenReturn(List.of(categoriaSimulada));

        List<CategoriaResponseDTO> resultado = categoriaService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Tecnologia", resultado.get(0).getNombre());
    }

    @Test
    void listarPorId_Exitoso() {
        when(repo.findById(1L)).thenReturn(Optional.of(categoriaSimulada));

        CategoriaResponseDTO resultado = categoriaService.listarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Tecnologia", resultado.getNombre());
    }

    @Test
    void listarPorId_Error_NoEncontrado() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> categoriaService.listarPorId(1L));
    }

    @Test
    void registrar_Exitoso() {
        when(repo.existsByNombre("Tecnologia")).thenReturn(false);
        when(repo.save(any(Categoria.class))).thenReturn(categoriaSimulada);

        CategoriaResponseDTO resultado = categoriaService.registrar(requestDto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Tecnologia", resultado.getNombre());
        verify(repo, times(1)).save(any(Categoria.class));
    }

    @Test
    void registrar_Error_YaExiste() {
        when(repo.existsByNombre("Tecnologia")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> categoriaService.registrar(requestDto));
        verify(repo, never()).save(any(Categoria.class));
    }
}