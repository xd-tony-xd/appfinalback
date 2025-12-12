package com.example.proyectofinalapp.service.impl;

import com.example.proyectofinalapp.entity.Categoria;
import com.example.proyectofinalapp.entity.Producto;
import com.example.proyectofinalapp.entity.Usuario;
import com.example.proyectofinalapp.repository.CategoriaRepository;
import com.example.proyectofinalapp.repository.ProductoRepository;
import com.example.proyectofinalapp.repository.UsuarioRepository;
import com.example.proyectofinalapp.service.ProductoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    // URL BASE
    @Value("${server.port}")
    private String serverPort;

    @Value("${app.host}")
    private String serverHost;

    public ProductoServiceImpl(ProductoRepository productoRepository,
                               UsuarioRepository usuarioRepository,
                               CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // --------------------------- CREAR SIN IMAGEN ----------------------------
    @Override
    public Producto crearProducto(Producto producto) {
        validarUsuarioYCategoria(producto);
        return productoRepository.save(producto);
    }

    // --------------------------- CREAR CON IMAGEN ----------------------------
    @Override
    public Producto crearProductoConImagen(Producto producto, MultipartFile imagen) {
        validarUsuarioYCategoria(producto);

        if (imagen != null && !imagen.isEmpty()) {
            String rutaPublica = guardarImagen(imagen);
            producto.setImagenUrl(rutaPublica);
        }

        return productoRepository.save(producto);
    }

    // --------------------------- ACTUALIZAR SIN IMAGEN ------------------------
    @Override
    public Producto actualizarProducto(Long id, Producto producto) {
        Producto existente = obtenerPorId(id);

        copiarCampos(producto, existente);

        if (producto.getImagenUrl() != null) {
            existente.setImagenUrl(producto.getImagenUrl());
        }

        validarCategoria(producto, existente);

        return productoRepository.save(existente);
    }

    // --------------------------- ACTUALIZAR CON IMAGEN -----------------------
    @Override
    public Producto actualizarProductoConImagen(Long id, Producto producto, MultipartFile imagen) {
        Producto existente = obtenerPorId(id);

        copiarCampos(producto, existente);

        if (imagen != null && !imagen.isEmpty()) {
            String rutaPublica = guardarImagen(imagen);
            existente.setImagenUrl(rutaPublica);
        }

        validarCategoria(producto, existente);

        return productoRepository.save(existente);
    }

    // --------------------------- COPIAR CAMPOS --------------------------------
    private void copiarCampos(Producto origen, Producto destino) {
        destino.setTitulo(origen.getTitulo());
        destino.setDescripcion(origen.getDescripcion());
        destino.setPrecio(origen.getPrecio());
        destino.setStock(origen.getStock());
        destino.setEstado(origen.getEstado());
        destino.setDireccion(origen.getDireccion());
        destino.setLatitud(origen.getLatitud());
        destino.setLongitud(origen.getLongitud());
    }

    // --------------------------- VALIDACIONES ---------------------------------
    private void validarUsuarioYCategoria(Producto producto) {

        Long usuarioId = producto.getUsuario().getId();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        producto.setUsuario(usuario);

        validarCategoria(producto, producto);
    }

    private void validarCategoria(Producto origen, Producto destino) {
        if (origen.getCategoria() != null && origen.getCategoria().getId() != null) {
            Categoria categoria = categoriaRepository.findById(origen.getCategoria().getId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            destino.setCategoria(categoria);
        }
    }

    // --------------------------- GUARDAR IMAGEN (CORREGIDO) -------------------
    private String guardarImagen(MultipartFile archivo) {

        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads/";

            File directorio = new File(uploadDir);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
            Path ruta = Paths.get(uploadDir + nombreArchivo);

            Files.copy(archivo.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

            // URL CORRECTA SIN DOBLE PUERTO
            return serverHost + ":" + serverPort + "/uploads/" + nombreArchivo;

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la imagen: " + e.getMessage());
        }
    }

    // --------------------------- CRUD -----------------------------------------
    @Override
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public List<Producto> listarPorUsuario(Long usuarioId) {
        return productoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}