package com.example.proyectofinalapp.service;

import com.example.proyectofinalapp.entity.Favorito;
import java.util.List;

public interface FavoritoService {
    Favorito agregarFavorito(Favorito favorito);
    List<Favorito> listarFavoritosPorUsuario(Long usuarioId);
    void eliminarFavorito(Long id);
}
