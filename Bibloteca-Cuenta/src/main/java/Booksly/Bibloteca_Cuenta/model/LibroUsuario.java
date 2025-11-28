package Booksly.Bibloteca_Cuenta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "libros_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String title;
    private String author;
    private String cover;
    private Integer totalPages;
    private Integer currentPage;
    private String status;
    private Integer timeReadInMinutes;
}