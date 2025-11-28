package Booksly.Bibloteca_Cuenta.service;

import Booksly.Bibloteca_Cuenta.dto.LibroDTO;
import Booksly.Bibloteca_Cuenta.dto.NuevoLibroDTO;
import Booksly.Bibloteca_Cuenta.dto.ProgresoDTO;
import Booksly.Bibloteca_Cuenta.model.LibroUsuario;
import Booksly.Bibloteca_Cuenta.repository.LibroUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BiblotecaService {

    @Autowired
    private LibroUsuarioRepository libroRepository;

    public List<LibroDTO> obtenerLibrosPorUsuario(Long userId) {
        List<LibroUsuario> entidades = libroRepository.findByUserId(userId);
        return entidades.stream()
                .map(this::convertirA_DTO)
                .collect(Collectors.toList());
    }

    public LibroDTO agregarLibro(NuevoLibroDTO libroDTO, Long userId) {
        LibroUsuario entidad = new LibroUsuario();
        entidad.setUserId(userId);
        entidad.setTitle(libroDTO.getTitle());
        entidad.setAuthor(libroDTO.getAuthor());
        entidad.setTotalPages(libroDTO.getTotalPages());
        entidad.setCover(libroDTO.getCover());
        
        entidad.setCurrentPage(0);
        entidad.setTimeReadInMinutes(0);
        entidad.setStatus("pendiente");

        LibroUsuario libroGuardado = libroRepository.save(entidad);
        return convertirA_DTO(libroGuardado);
    }

    public LibroDTO actualizarProgreso(Long libroId, ProgresoDTO progresoDTO) {
        LibroUsuario libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + libroId));

        libro.setCurrentPage(progresoDTO.getCurrentPage());
        libro.setTimeReadInMinutes(progresoDTO.getTimeReadInMinutes());
        libro.setStatus(progresoDTO.getStatus());

        LibroUsuario libroActualizado = libroRepository.save(libro);
        return convertirA_DTO(libroActualizado);
    }

    private LibroDTO convertirA_DTO(LibroUsuario entidad) {
        LibroDTO dto = new LibroDTO();
        dto.setId(entidad.getId());
        dto.setTitle(entidad.getTitle());
        dto.setAuthor(entidad.getAuthor());
        dto.setCover(entidad.getCover());
        dto.setTotalPages(entidad.getTotalPages());
        dto.setCurrentPage(entidad.getCurrentPage());
        dto.setStatus(entidad.getStatus());
        dto.setTimeReadInMinutes(entidad.getTimeReadInMinutes());
        return dto;
    }
}