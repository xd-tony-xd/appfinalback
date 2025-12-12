package com.example.proyectofinalapp.service;

import com.example.proyectofinalapp.entity.Categoria;
import java.util.List;

public interface CategoriaService {
    Categoria crearCategoria(Categoria categoria);
    List<Categoria> listarCategorias();
    Categoria obtenerPorId(Long id);
    Categoria actualizarCategoria(Long id, Categoria categoria);
    void eliminarCategoria(Long id);
}
