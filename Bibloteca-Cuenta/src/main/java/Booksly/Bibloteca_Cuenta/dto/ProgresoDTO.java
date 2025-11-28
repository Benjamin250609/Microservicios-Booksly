package Booksly.Bibloteca_Cuenta.dto;

import lombok.Data;

@Data
public class ProgresoDTO {
    private Integer currentPage;
    private Integer timeReadInMinutes;
    private String status;
}