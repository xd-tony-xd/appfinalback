package com.example.proyectofinalapp.service;

import com.example.proyectofinalapp.entity.Mensaje;
import java.util.List;

public interface MensajeService {
    Mensaje enviarMensaje(Mensaje mensaje);
    List<Mensaje> obtenerConversacion(Long remitenteId, Long receptorId);
}
