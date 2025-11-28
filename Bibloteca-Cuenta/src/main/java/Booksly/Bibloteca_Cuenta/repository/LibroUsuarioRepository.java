package Booksly.Bibloteca_Cuenta.repository;

import Booksly.Bibloteca_Cuenta.model.LibroUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroUsuarioRepository extends JpaRepository<LibroUsuario, Long> {

    // Spring Data JPA creará la consulta:
    // "SELECT * FROM libros_usuario WHERE user_id = ?"
    List<LibroUsuario> findByUserId(Long userId);
}
