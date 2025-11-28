package Booksly.Bibloteca_Cuenta;

import Booksly.Bibloteca_Cuenta.model.Usuario;
import Booksly.Bibloteca_Cuenta.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BiblotecaCuentaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiblotecaCuentaApplication.class, args);
    }

    // --- CÓDIGO DE DIAGNÓSTICO ---
    @Bean
    public CommandLineRunner demo(UsuarioRepository repository) {
        return (args) -> {
            System.out.println("\n============================================");
            System.out.println("🕵️‍♂️ INICIANDO DIAGNÓSTICO DE BASE DE DATOS");
            System.out.println("============================================");

            List<Usuario> usuarios = repository.findAll();

            System.out.println("📊 Cantidad de usuarios encontrados: " + usuarios.size());

            if (usuarios.isEmpty()) {
                System.out.println("⚠️ LA TABLA ESTÁ VACÍA. Java no ve ningún dato.");
                System.out.println("   -> Verifica que la URL en application.properties sea correcta.");
                System.out.println("   -> Verifica que en Workbench hayas hecho COMMIT (o el botón del rayo).");
            } else {
                System.out.println("✅ Usuarios encontrados:");
                for (Usuario u : usuarios) {
                    System.out.println("   🆔 ID: " + u.getId() + " | 📧 Email: " + u.getEmail() + " | 🔑 Pass: " + u.getPassword());
                }
            }
            System.out.println("============================================\n");
        };
    }
}