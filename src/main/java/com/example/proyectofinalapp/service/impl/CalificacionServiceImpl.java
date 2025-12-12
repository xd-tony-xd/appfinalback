package com.example.proyectofinalapp.service.impl;

import com.example.proyectofinalapp.entity.Calificacion;
import com.example.proyectofinalapp.repository.CalificacionRepository;
import com.example.proyectofinalapp.service.CalificacionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionServiceImpl implements CalificacionService {

    private final CalificacionRepository calificacionRepository;

    public CalificacionServiceImpl(CalificacionRepository calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
    }

    @Override
    public Calificacion agregarCalificacion(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    @Override
    public List<Calificacion> listarPorUsuario(Long calificadoId) {
        return calificacionRepository.findByCalificadoId(calificadoId);
    }

    @Override
    public Double promedioCalificaciones(Long calificadoId) {
        List<Calificacion> calificaciones = listarPorUsuario(calificadoId);
        if (calificaciones.isEmpty()) return 0.0;
        return calificaciones.stream()
                .mapToDouble(Calificacion::getPuntuacion)
                .average()
                .orElse(0.0);
    }
}
