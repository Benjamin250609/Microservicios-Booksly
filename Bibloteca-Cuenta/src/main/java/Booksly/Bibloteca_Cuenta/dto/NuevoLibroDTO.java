package Booksly.Bibloteca_Cuenta.dto;

import lombok.Data;

@Data
public class NuevoLibroDTO {
    private String title;
    private String author;
    private Integer totalPages;
    private String cover;
}