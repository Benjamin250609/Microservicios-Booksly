package Booksly.social.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long userId;
    private String usuarioNombre;
    private String usuarioAvatar;


    private String libroTitulo;
    private String libroAutor;
    private String libroPortada;


    private String accion;
    private int likesCount = 0;

    private LocalDateTime fechaCreacion;


    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }
}