package com.grupo.cotizador.client;
import com.grupo.cotizador.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-cliente", url = "${cliente.service.url}")
public interface ClienteClient {
    @GetMapping("/api/clientes/{id}")
    ClienteDTO obtenerCliente(@PathVariable("id") Long id);
}