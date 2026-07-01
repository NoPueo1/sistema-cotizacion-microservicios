package com.grupo.cotizador.service;
import com.grupo.cotizador.dto.*;
import com.grupo.cotizador.model.Cotizacion;
import com.grupo.cotizador.repository.CotizacionRepository;
import com.grupo.cotizador.client.ClienteClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class CotizacionService {
    private final CotizacionRepository repo;
    private final ClienteClient clienteClient;

    public List<CotizacionResponseDTO> listar() { return repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList()); }
    public CotizacionResponseDTO crear(CotizacionRequestDTO dto) {
        Cotizacion c = repo.save(new Cotizacion(null, dto.getClienteId(), null, dto.getTotal()));
        return mapToDTO(c);
    }
    private CotizacionResponseDTO mapToDTO(Cotizacion c) {
        String nombre = "Desconocido";
        try { ClienteDTO cli = clienteClient.obtenerCliente(c.getClienteId()); nombre = cli.getNombre(); } catch(Exception e){}
        return new CotizacionResponseDTO(c.getId(), c.getClienteId(), nombre, c.getFecha(), c.getTotal());
    }
}