package com.grupo.equipo.service;
import com.grupo.equipo.dto.*;
import com.grupo.equipo.model.Equipo;
import com.grupo.equipo.repository.EquipoRepository;
import com.grupo.equipo.client.CategoriaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class EquipoService {
    private final EquipoRepository repo;
    private final CategoriaClient categoriaClient;

    public List<EquipoResponseDTO> listarTodos() { return repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList()); }
    @Transactional
    public EquipoResponseDTO registrar(EquipoRequestDTO dto) {
        if(repo.existsByCodigoSku(dto.getCodigoSku())) throw new RuntimeException("El SKU ya existe");
        return mapToDTO(repo.save(new Equipo(null, dto.getNombre(), dto.getCodigoSku(), dto.getPrecio(), dto.getCategoriaId())));
    }
    private EquipoResponseDTO mapToDTO(Equipo e) {
        String catName = "Desconocida";
        try {
            CategoriaDTO cat = categoriaClient.obtenerCategoria(e.getCategoriaId());
            catName = (cat != null) ? cat.getNombre() : catName;
        } catch (Exception ex) {}
        return new EquipoResponseDTO(e.getId(), e.getNombre(), e.getCodigoSku(), e.getPrecio(), e.getCategoriaId(), catName);
    }
}