package com.grupo.itemcotizador.service;

import com.grupo.itemcotizador.dto.ItemRequestDTO;
import com.grupo.itemcotizador.model.ItemCotizacion;
import com.grupo.itemcotizador.repository.ItemCotizacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemCotizacionServiceTest {

    @Mock
    private ItemCotizacionRepository repo;

    @InjectMocks
    private ItemCotizacionService itemCotizacionService;

    private ItemCotizacion itemSimulado;
    private ItemRequestDTO requestDto;

    @BeforeEach
    void setUp() {
        requestDto = new ItemRequestDTO(100L, 50L, 2, 150.0);
        itemSimulado = new ItemCotizacion(1L, 100L, 50L, 2, 150.0);
    }

    @Test
    void porCotizacion_Exitoso() {
        when(repo.findByCotizacionId(100L)).thenReturn(List.of(itemSimulado));

        List<ItemCotizacion> resultado = itemCotizacionService.porCotizacion(100L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(100L, resultado.get(0).getCotizacionId());
    }

    @Test
    void agregar_Exitoso() {
        when(repo.save(any(ItemCotizacion.class))).thenReturn(itemSimulado);

        ItemCotizacion resultado = itemCotizacionService.agregar(requestDto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(50L, resultado.getEquipoId());
        verify(repo, times(1)).save(any(ItemCotizacion.class));
    }
}