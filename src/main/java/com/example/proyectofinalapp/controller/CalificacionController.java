package com.example.proyectofinalapp.controller;

import com.example.proyectofinalapp.entity.Calificacion;
import com.example.proyectofinalapp.service.CalificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calificaciones")
@CrossOrigin(origins = "*")
public class CalificacionController {

    private final CalificacionService calificacionService;

    public CalificacionController(CalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

    @PostMapping
    public ResponseEntity<Calificacion> agregar(@RequestBody Calificacion calificacion) {
        return ResponseEntity.ok(calificacionService.agregarCalificacion(calificacion));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Calificacion>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(calificacionService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/usuario/{usuarioId}/promedio")
    public ResponseEntity<Double> promedio(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(calificacionService.promedioCalificaciones(usuarioId));
    }
}

