package Booksly.Bibloteca_Cuenta.service;


import Booksly.Bibloteca_Cuenta.dto.AuthDTOs;
import Booksly.Bibloteca_Cuenta.model.Usuario;
import Booksly.Bibloteca_Cuenta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public AuthDTOs.UsuarioResponse registrar(AuthDTOs.RegisterRequest request) {
        // Validaciones básicas
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());
        usuario.setFechaNacimiento(request.getFechaNacimiento()); // Guardamos la fecha

        Usuario guardado = usuarioRepository.save(usuario);
        return mapearAResponse(guardado);
    }

    public AuthDTOs.UsuarioResponse login(AuthDTOs.LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return mapearAResponse(usuario);
    }

    private AuthDTOs.UsuarioResponse mapearAResponse(Usuario usuario) {
        AuthDTOs.UsuarioResponse response = new AuthDTOs.UsuarioResponse();
        response.setId(usuario.getId());
        response.setUsername(usuario.getUsername());
        response.setEmail(usuario.getEmail());
        response.setFechaNacimiento(usuario.getFechaNacimiento());
        return response;
    }
}