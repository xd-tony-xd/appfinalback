package com.example.proyectofinalapp.service.impl;

import com.example.proyectofinalapp.entity.Mensaje;
import com.example.proyectofinalapp.repository.MensajeRepository;
import com.example.proyectofinalapp.service.MensajeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeServiceImpl implements MensajeService {

    private final MensajeRepository mensajeRepository;

    public MensajeServiceImpl(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }

    @Override
    public Mensaje enviarMensaje(Mensaje mensaje) {
        mensaje.setFechaEnvio(new java.util.Date());
        return mensajeRepository.save(mensaje);
    }

    @Override
    public List<Mensaje> obtenerConversacion(Long remitenteId, Long receptorId) {
        return mensajeRepository.findByRemitenteIdAndReceptorIdOrReceptorIdAndRemitenteId(
                remitenteId, receptorId, remitenteId, receptorId
        );
    }
}
