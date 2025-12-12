package com.example.proyectofinalapp.repository;

import com.example.proyectofinalapp.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByRemitenteIdAndReceptorIdOrReceptorIdAndRemitenteId(
            Long remitenteId1, Long receptorId1, Long remitenteId2, Long receptorId2);
}
