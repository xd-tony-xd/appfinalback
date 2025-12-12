package com.example.proyectofinalapp.service;

import com.example.proyectofinalapp.entity.Producto;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ProductoService {

    // Crear SIN imagen (poco útil, pero mantenido para compatibilidad)
    Producto crearProducto(Producto producto);

    // Crear CON imagen
    Producto crearProductoConImagen(Producto producto, MultipartFile imagen);

    Producto obtenerPorId(Long id);

    List<Producto> listarProductos();

    List<Producto> listarPorUsuario(Long usuarioId);

    // Actualizar SIN imagen (opcional)
    Producto actualizarProducto(Long id, Producto producto);

    // ✔ Actualizar CON imagen (NECESARIO)
    Producto actualizarProductoConImagen(Long id, Producto producto, MultipartFile imagen);

    void eliminarProducto(Long id);
}
