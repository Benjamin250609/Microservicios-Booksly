package Booksly.Bibloteca_Cuenta.repository;


import Booksly.Bibloteca_Cuenta.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    // Para verificar si ya existe al registrarse
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}