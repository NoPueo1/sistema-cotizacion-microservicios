package com.grupo.stock.service;

import com.grupo.stock.model.Stock;
import com.grupo.stock.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private StockRepository repo;

    @InjectMocks
    private StockService stockService;

    private Stock stockSimulado;

    @BeforeEach
    void setUp() {
        stockSimulado = new Stock(1L, 50L, 10);
    }

    @Test
    void obtenerPorEquipo_Exitoso() {
        when(repo.findByEquipoId(50L)).thenReturn(Optional.of(stockSimulado));

        Stock resultado = stockService.obtenerPorEquipo(50L);

        assertNotNull(resultado);
        assertEquals(50L, resultado.getEquipoId());
        assertEquals(10, resultado.getCantidadDisponible());
    }

    @Test
    void obtenerPorEquipo_Error_NoEncontrado() {
        when(repo.findByEquipoId(50L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> stockService.obtenerPorEquipo(50L));
    }

    @Test
    void actualizar_StockExistente() {
        when(repo.findByEquipoId(50L)).thenReturn(Optional.of(stockSimulado));
        when(repo.save(any(Stock.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Stock resultado = stockService.actualizar(50L, 5);

        assertNotNull(resultado);
        assertEquals(15, resultado.getCantidadDisponible()); // 10 base + 5 sumados
        verify(repo, times(1)).save(any(Stock.class));
    }

    @Test
    void actualizar_StockNuevo() {
        when(repo.findByEquipoId(50L)).thenReturn(Optional.empty());
        when(repo.save(any(Stock.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Stock resultado = stockService.actualizar(50L, 8);

        assertNotNull(resultado);
        assertEquals(50L, resultado.getEquipoId());
        assertEquals(8, resultado.getCantidadDisponible()); // 0 base + 8 sumados
        verify(repo, times(1)).save(any(Stock.class));
    }
}