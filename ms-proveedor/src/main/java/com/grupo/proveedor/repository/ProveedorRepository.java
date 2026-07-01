package com.grupo.proveedor.repository;
import com.grupo.proveedor.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    boolean existsByRut(String rut);
}