package com.grupo.facturacion.service;
import com.grupo.facturacion.dto.*;
import com.grupo.facturacion.model.Factura;
import com.grupo.facturacion.repository.FacturaRepository;
import com.grupo.facturacion.client.ClienteClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class FacturaService {
    private final FacturaRepository repo;
    private final ClienteClient clienteClient;

    public List<FacturaResponseDTO> listarTodas() { return repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList()); }
    @Transactional
    public FacturaResponseDTO emitir(FacturaRequestDTO dto) {
        if(repo.existsBySerieAndNumero(dto.getSerie(), dto.getNumero())) throw new RuntimeException("El número de factura ya existe");
        return mapToDTO(repo.save(new Factura(null, dto.getSerie(), dto.getNumero(), null, dto.getTotal(), dto.getClienteId(), dto.getCotizacionId())));
    }
    private FacturaResponseDTO mapToDTO(Factura f) {
        String cliName = "Desconocido";
        try {
            ClienteDTO cli = clienteClient.obtenerCliente(f.getClienteId());
            cliName = (cli != null) ? cli.getNombre() : cliName;
        } catch (Exception ex) {}
        return new FacturaResponseDTO(f.getId(), f.getSerie() + "-" + f.getNumero(), f.getFechaEmision(), f.getTotal(), cliName);
    }
}