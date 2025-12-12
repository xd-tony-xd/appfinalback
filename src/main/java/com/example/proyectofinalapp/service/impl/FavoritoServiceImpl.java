package com.example.proyectofinalapp.service.impl;

import com.example.proyectofinalapp.entity.Favorito;
import com.example.proyectofinalapp.repository.FavoritoRepository;
import com.example.proyectofinalapp.service.FavoritoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoServiceImpl implements FavoritoService {

    private final FavoritoRepository favoritoRepository;

    public FavoritoServiceImpl(FavoritoRepository favoritoRepository) {
        this.favoritoRepository = favoritoRepository;
    }

    @Override
    public Favorito agregarFavorito(Favorito favorito) {
        return favoritoRepository.save(favorito);
    }

    @Override
    public List<Favorito> listarFavoritosPorUsuario(Long usuarioId) {
        return favoritoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public void eliminarFavorito(Long id) {
        favoritoRepository.deleteById(id);
    }
}
