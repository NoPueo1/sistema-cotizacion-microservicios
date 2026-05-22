package com.grupo.cliente.repository;
import com.grupo.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByDocumentoIdentidad(String documentoIdentidad);
}