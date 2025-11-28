package Booksly.Bibloteca_Cuenta.dto;

import lombok.Data;

@Data
public class LibroDTO {
    private Long id;
    private String title;
    private String author;
    private String cover;
    private Integer totalPages;
    private Integer currentPage;
    private String status;
    private Integer timeReadInMinutes;
}