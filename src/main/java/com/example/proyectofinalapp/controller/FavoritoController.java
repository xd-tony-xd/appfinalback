package com.example.proyectofinalapp.controller;

import com.example.proyectofinalapp.entity.Favorito;
import com.example.proyectofinalapp.service.FavoritoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
@CrossOrigin(origins = "*")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @PostMapping
    public ResponseEntity<Favorito> agregar(@RequestBody Favorito favorito) {
        return ResponseEntity.ok(favoritoService.agregarFavorito(favorito));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Favorito>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(favoritoService.listarFavoritosPorUsuario(usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        favoritoService.eliminarFavorito(id);
        return ResponseEntity.noContent().build();
    }
}
