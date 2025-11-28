package Booksly.Bibloteca_Cuenta.dto;

import lombok.Data;
import java.time.LocalDate;

public class AuthDTOs {

    @Data
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Data
    public static class RegisterRequest {
        private String username;
        private String email;
        private String password;
        private LocalDate fechaNacimiento;
    }

    @Data
    public static class UsuarioResponse {
        private Long id;
        private String username;
        private String email;
        private LocalDate fechaNacimiento;
    }
}