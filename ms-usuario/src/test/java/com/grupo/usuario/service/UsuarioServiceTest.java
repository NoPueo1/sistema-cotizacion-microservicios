package com.grupo.usuario.service;

import com.grupo.usuario.dto.LoginDTO;
import com.grupo.usuario.dto.UsuarioRequestDTO;
import com.grupo.usuario.model.Usuario;
import com.grupo.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repo;

    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioRequestDTO registroDto;
    private LoginDTO loginDto;
    private Usuario usuarioSimulado;

    @BeforeEach
    void setUp() {
        registroDto = new UsuarioRequestDTO("juan123", "password125", "ADMIN");
        loginDto = new LoginDTO("juan123", "password125");
        usuarioSimulado = new Usuario(1L, "juan123", "password125", "ADMIN");
    }

    @Test
    void registrar_Exitoso() {
        when(repo.existsByUsername(registroDto.getUsername())).thenReturn(false);
        when(repo.save(any(Usuario.class))).thenReturn(usuarioSimulado);

        Usuario resultado = usuarioService.registrar(registroDto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("juan123", resultado.getUsername());
        verify(repo, times(1)).save(any(Usuario.class));
    }

    @Test
    void registrar_Error_UsuarioYaExiste() {
        when(repo.existsByUsername(registroDto.getUsername())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.registrar(registroDto);
        });

        assertEquals("Usuario ya existe", exception.getMessage());
        verify(repo, never()).save(any(Usuario.class));
    }

    @Test
    void login_Exitoso() {
        when(repo.findByUsername(loginDto.getUsername())).thenReturn(Optional.of(usuarioSimulado));

        String resultado = usuarioService.login(loginDto);

        assertEquals("Login exitoso. Rol: ADMIN", resultado);
    }

    @Test
    void login_Error_CredencialesInvalidas_UsuarioNoExiste() {
        when(repo.findByUsername(loginDto.getUsername())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            usuarioService.login(loginDto);
        });
    }

    @Test
    void login_Error_CredencialesInvalidas_PasswordIncorrecto() {
        LoginDTO dtoPasswordErroneo = new LoginDTO("juan123", "clave_incorrecta");
        when(repo.findByUsername(dtoPasswordErroneo.getUsername())).thenReturn(Optional.of(usuarioSimulado));

        assertThrows(RuntimeException.class, () -> {
            usuarioService.login(dtoPasswordErroneo);
        });
    }
}