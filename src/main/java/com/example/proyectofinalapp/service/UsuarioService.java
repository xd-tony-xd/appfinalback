package com.example.proyectofinalapp.service;

import com.example.proyectofinalapp.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UsuarioService {

    Usuario registrarUsuario(Usuario usuario);

    Usuario obtenerPorEmail(String email) throws UsernameNotFoundException;

    Usuario obtenerPorId(Long id);

    List<Usuario> listarUsuarios();

    Usuario actualizarUsuario(Long id, Usuario usuarioActualizado);

    void eliminarUsuario(Long id);

    boolean existePorEmail(String email); // Para validar duplicados

    UserDetails cargarUsuarioPorEmail(String email) throws UsernameNotFoundException;
}
