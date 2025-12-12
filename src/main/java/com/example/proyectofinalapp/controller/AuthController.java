package com.example.proyectofinalapp.controller;

import com.example.proyectofinalapp.entity.Usuario;
import com.example.proyectofinalapp.security.JwtUtil;
import com.example.proyectofinalapp.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioService usuarioService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            Usuario nuevo = usuarioService.registrarUsuario(usuario);
            String token = jwtUtil.generarToken(nuevo.getEmail());
            return ResponseEntity.ok(Map.of("usuario", nuevo, "token", token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        Usuario usuario;
        try {
            usuario = usuarioService.obtenerPorEmail(email);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Usuario no encontrado"));
        }

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("error", "Contraseña incorrecta"));
        }

        String token = jwtUtil.generarToken(email);
        return ResponseEntity.ok(Map.of("usuario", usuario, "token", token));
    }
}
