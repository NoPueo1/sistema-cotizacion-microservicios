package com.grupo.equipo.repository;
import com.grupo.equipo.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    boolean existsByCodigoSku(String codigoSku);
}