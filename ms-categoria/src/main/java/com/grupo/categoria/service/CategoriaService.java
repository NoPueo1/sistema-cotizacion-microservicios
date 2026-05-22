package com.grupo.categoria.service;
import com.grupo.categoria.dto.*;
import com.grupo.categoria.model.Categoria;
import com.grupo.categoria.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository repo;
    public List<CategoriaResponseDTO> listarTodos() {
        return repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public CategoriaResponseDTO listarPorId(Long id) {
        return repo.findById(id).map(this::mapToDTO).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }
    @Transactional
    public CategoriaResponseDTO registrar(CategoriaRequestDTO dto) {
        if (repo.existsByNombre(dto.getNombre())) throw new RuntimeException("La categoría ya existe");
        return mapToDTO(repo.save(new Categoria(null, dto.getNombre(), dto.getDescripcion())));
    }
    private CategoriaResponseDTO mapToDTO(Categoria c) {
        return new CategoriaResponseDTO(c.getId(), c.getNombre(), c.getDescripcion());
    }
}