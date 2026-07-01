package com.grupo.usuario.service;
import com.grupo.usuario.dto.*;
import com.grupo.usuario.model.Usuario;
import com.grupo.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repo;
    public Usuario registrar(UsuarioRequestDTO dto) {
        if(repo.existsByUsername(dto.getUsername())) throw new RuntimeException("Usuario ya existe");
        return repo.save(new Usuario(null, dto.getUsername(), dto.getPassword(), dto.getRol()));
    }
    public String login(LoginDTO dto) {
        Usuario u = repo.findByUsername(dto.getUsername()).orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
        if(!u.getPassword().equals(dto.getPassword())) throw new RuntimeException("Credenciales inválidas");
        return "Login exitoso. Rol: " + u.getRol();
    }
}
