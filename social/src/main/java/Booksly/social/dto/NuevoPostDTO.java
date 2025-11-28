package Booksly.social.dto;

import lombok.Data;

@Data
public class NuevoPostDTO {

    private Long userId;
    private String usuarioNombre;

    // Info del Libro
    private String libroTitulo;
    private String libroAutor;
    private String libroPortada;


    private String accion;
    private String comentario;
}