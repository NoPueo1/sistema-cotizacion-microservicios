package com.grupo.categoria.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo.categoria.model.Categoria;
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNombre(String nombre);
}