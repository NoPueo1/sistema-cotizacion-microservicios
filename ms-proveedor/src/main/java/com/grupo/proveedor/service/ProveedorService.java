package com.grupo.proveedor.service;
import com.grupo.proveedor.dto.*;
import com.grupo.proveedor.model.Proveedor;
import com.grupo.proveedor.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class ProveedorService {
    private final ProveedorRepository repo;
    public List<ProveedorResponseDTO> listarTodos() { return repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList()); }
    @Transactional
    public ProveedorResponseDTO registrar(ProveedorRequestDTO dto) {
        if(repo.existsByRut(dto.getRut())) throw new RuntimeException("El RUT ya existe");
        return mapToDTO(repo.save(new Proveedor(null, dto.getRazonSocial(), dto.getRut(), dto.getDireccion())));
    }
    private ProveedorResponseDTO mapToDTO(Proveedor p) { return new ProveedorResponseDTO(p.getId(), p.getRazonSocial(), p.getRut(), p.getDireccion()); }
}