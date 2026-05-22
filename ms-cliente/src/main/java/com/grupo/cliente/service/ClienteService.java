package com.grupo.cliente.service;
import com.grupo.cliente.dto.*;
import com.grupo.cliente.model.Cliente;
import com.grupo.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repo;
    public List<ClienteResponseDTO> listarTodos() { return repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList()); }
    public ClienteResponseDTO listarPorId(Long id) { return repo.findById(id).map(this::mapToDTO).orElseThrow(() -> new RuntimeException("Cliente no encontrado")); }
    @Transactional
    public ClienteResponseDTO registrar(ClienteRequestDTO dto) {
        if(repo.existsByDocumentoIdentidad(dto.getDocumentoIdentidad())) throw new RuntimeException("El documento ya existe");
        return mapToDTO(repo.save(new Cliente(null, dto.getNombre(), dto.getDocumentoIdentidad(), dto.getCorreo(), dto.getTelefono())));
    }
    private ClienteResponseDTO mapToDTO(Cliente c) { return new ClienteResponseDTO(c.getId(), c.getNombre(), c.getDocumentoIdentidad(), c.getCorreo(), c.getTelefono()); }
}