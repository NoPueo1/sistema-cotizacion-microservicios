package com.grupo.notificacion.repository;
import com.grupo.notificacion.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {}