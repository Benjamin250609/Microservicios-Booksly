package Booksly.social.dto;



import lombok.Data;
import java.util.List;

@Data
public class PostDTO {
    private Long id;
    private String usuario;
    private String avatar;
    private String accion;
    private String tiempo;


    private int likes;
    private List<ComentarioDTO> comentarios;

    private LibroInfo libro;

    @Data
    public static class LibroInfo {
        private String titulo;
        private String autor;
        private String portada;
    }
}
