package com.example.proyectofinalapp.controller;

import com.example.proyectofinalapp.entity.Categoria;
import com.example.proyectofinalapp.entity.Producto;
import com.example.proyectofinalapp.entity.Usuario;
import com.example.proyectofinalapp.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }


    // -------------------------------------------------------------------------
    // CREAR PRODUCTO CON IMAGEN (MULTIPART)
    // -------------------------------------------------------------------------
    @PostMapping(
            value = "/crear",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Producto> crear(
            @RequestPart("producto") String productoJson,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen
    ) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.readValue(productoJson, Producto.class);

        // ---------------------------------------------------------------------
        // RECONSTRUIR USUARIO Y CATEGORIA PARA EVITAR ERROR 500
        // ---------------------------------------------------------------------
        Long usuarioId = producto.getUsuario() != null ? producto.getUsuario().getId() : null;
        Long categoriaId = (producto.getCategoria() != null) ? producto.getCategoria().getId() : null;

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        producto.setUsuario(usuario);

        if (categoriaId != null) {
            Categoria categoria = new Categoria();
            categoria.setId(categoriaId);
            producto.setCategoria(categoria);
        }

        Producto creado = productoService.crearProductoConImagen(producto, imagen);
        return ResponseEntity.ok(creado);
    }


    // -------------------------------------------------------------------------
    // ACTUALIZAR PRODUCTO CON O SIN IMAGEN (MULTIPART)
    // -------------------------------------------------------------------------
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Producto> actualizar(
            @PathVariable Long id,
            @RequestPart("producto") String productoJson,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen
    ) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.readValue(productoJson, Producto.class);

        // ---------------------------------------------------------------------
        // RECONSTRUIR USUARIO Y CATEGORIA PARA EVITAR ERROR 500
        // ---------------------------------------------------------------------
        Long usuarioId = producto.getUsuario() != null ? producto.getUsuario().getId() : null;
        Long categoriaId = (producto.getCategoria() != null) ? producto.getCategoria().getId() : null;

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        producto.setUsuario(usuario);

        if (categoriaId != null) {
            Categoria categoria = new Categoria();
            categoria.setId(categoriaId);
            producto.setCategoria(categoria);
        }

        Producto actualizado = productoService.actualizarProductoConImagen(id, producto, imagen);
        return ResponseEntity.ok(actualizado);
    }


    // -------------------------------------------------------------------------
    // OBTENER POR ID
    // -------------------------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }


    // -------------------------------------------------------------------------
    // LISTAR TODOS
    // -------------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listarProductos());
    }


    // -------------------------------------------------------------------------
    // LISTAR POR USUARIO
    // -------------------------------------------------------------------------
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Producto>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(productoService.listarPorUsuario(usuarioId));
    }


    // -------------------------------------------------------------------------
    // ELIMINAR
    // -------------------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
