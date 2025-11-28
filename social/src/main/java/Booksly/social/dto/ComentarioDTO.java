package Booksly.social.dto;


import lombok.Data;

@Data
public class ComentarioDTO {
    private Long id;
    private String usuario;
    private String texto;
    private String tiempo;
}