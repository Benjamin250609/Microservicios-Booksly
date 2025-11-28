package Booksly.Bibloteca_Cuenta.controller;

import Booksly.Bibloteca_Cuenta.dto.LibroDTO;
import Booksly.Bibloteca_Cuenta.dto.NuevoLibroDTO;
import Booksly.Bibloteca_Cuenta.dto.ProgresoDTO;
import Booksly.Bibloteca_Cuenta.service.BiblotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
@CrossOrigin(origins = "*")
public class BibliotecaController {

    @Autowired
    private BiblotecaService bibliotecaService;

    // 1. OBTENER LIBROS (Ahora es obligatorio enviar ?userId=X desde el front)
    @GetMapping("/books")
    public List<LibroDTO> getLibrosDelUsuario(@RequestParam Long userId) {
        return bibliotecaService.obtenerLibrosPorUsuario(userId);
    }

    // 2. AGREGAR LIBRO (Obligatorio enviar ?userId=X)
    @PostMapping("/books")
    public LibroDTO agregarLibro(@RequestBody NuevoLibroDTO libroDTO, @RequestParam Long userId) {
        return bibliotecaService.agregarLibro(libroDTO, userId);
    }

    // 3. ACTUALIZAR PROGRESO
    // Nota: Aquí no hace falta el userId porque el ID del libro ya es único
    @PatchMapping("/books/{id}/progress")
    public LibroDTO actualizarProgreso(@PathVariable Long id, @RequestBody ProgresoDTO progresoDTO) {
        return bibliotecaService.actualizarProgreso(id, progresoDTO);
    }
}