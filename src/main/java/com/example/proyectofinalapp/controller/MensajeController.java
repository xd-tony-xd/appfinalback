package com.example.proyectofinalapp.controller;

import com.example.proyectofinalapp.entity.Mensaje;
import com.example.proyectofinalapp.service.MensajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
@CrossOrigin(origins = "*")
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @PostMapping
    public ResponseEntity<Mensaje> enviar(@RequestBody Mensaje mensaje) {
        return ResponseEntity.ok(mensajeService.enviarMensaje(mensaje));
    }

    @GetMapping("/conversacion")
    public ResponseEntity<List<Mensaje>> obtenerConversacion(
            @RequestParam Long remitenteId,
            @RequestParam Long receptorId) {
        return ResponseEntity.ok(mensajeService.obtenerConversacion(remitenteId, receptorId));
    }
}
