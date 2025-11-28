package Booksly.Bibloteca_Cuenta.controller;


import Booksly.Bibloteca_Cuenta.dto.AuthDTOs;
import Booksly.Bibloteca_Cuenta.service.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioServices usuarioService;

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody AuthDTOs.RegisterRequest request) {
        try {
            AuthDTOs.UsuarioResponse response = usuarioService.registrar(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTOs.LoginRequest request) {
        try {
            AuthDTOs.UsuarioResponse response = usuarioService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
