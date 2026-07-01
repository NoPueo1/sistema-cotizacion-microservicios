package com.grupo.equipo.client;
import com.grupo.equipo.dto.CategoriaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "ms-categoria", url = "${categoria.service.url}")
public interface CategoriaClient {
    @GetMapping("/api/categorias/{id}")
    CategoriaDTO obtenerCategoria(@PathVariable("id") Long id);
}