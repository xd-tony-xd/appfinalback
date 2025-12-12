package com.example.proyectofinalapp.service;

import com.example.proyectofinalapp.entity.Calificacion;
import java.util.List;

public interface CalificacionService {
    Calificacion agregarCalificacion(Calificacion calificacion);
    List<Calificacion> listarPorUsuario(Long calificadoId);
    Double promedioCalificaciones(Long calificadoId);
}
