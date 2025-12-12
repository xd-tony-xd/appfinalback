package com.example.proyectofinalapp.repository;

import com.example.proyectofinalapp.entity.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    List<Calificacion> findByCalificadoId(Long calificadoId);
}
