package com.grupo.equipo.service;

import com.grupo.equipo.client.CategoriaClient;
import com.grupo.equipo.dto.CategoriaDTO;
import com.grupo.equipo.dto.EquipoRequestDTO;
import com.grupo.equipo.dto.EquipoResponseDTO;
import com.grupo.equipo.model.Equipo;
import com.grupo.equipo.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoRepository repo;
    private final CategoriaClient categoriaClient;

    public List<EquipoResponseDTO> listarTodos() {
        return repo.findAll().stream().map(equipo -> {
            CategoriaDTO cat = categoriaClient.obtenerCategoria(equipo.getCategoriaId());
            return new EquipoResponseDTO(
                    equipo.getId(),
                    equipo.getNombre(),
                    equipo.getCodigoSku(),
                    equipo.getPrecio(),
                    cat.getNombre()
            );
        }).collect(Collectors.toList());
    }

    public EquipoResponseDTO registrar(EquipoRequestDTO dto) {
        if (repo.existsByCodigoSku(dto.getCodigoSku())) {
            throw new RuntimeException("SKU ya existe");
        }
        
        Equipo equipo = new Equipo(null, dto.getNombre(), dto.getCodigoSku(), dto.getPrecio(), dto.getCategoriaId(), null, null);
        Equipo guardado = repo.save(equipo);
        
        CategoriaDTO cat = categoriaClient.obtenerCategoria(guardado.getCategoriaId());
        
        return new EquipoResponseDTO(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getCodigoSku(),
                guardado.getPrecio(),
                cat.getNombre()
        );
    }

    public EquipoResponseDTO obtenerPorId(Long id) {
        Equipo equipo = repo.findById(id).orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        CategoriaDTO cat = categoriaClient.obtenerCategoria(equipo.getCategoriaId());
        return new EquipoResponseDTO(
                equipo.getId(),
                equipo.getNombre(),
                equipo.getCodigoSku(),
                equipo.getPrecio(),
                cat.getNombre()
        );
    }
}