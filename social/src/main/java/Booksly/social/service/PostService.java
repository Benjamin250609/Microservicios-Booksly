package Booksly.social.service;


import Booksly.social.dto.ComentarioDTO;
import Booksly.social.dto.NuevoPostDTO;
import Booksly.social.dto.PostDTO;
import Booksly.social.model.Comentario;
import Booksly.social.model.Post;
import Booksly.social.repository.ComentarioRepository;
import Booksly.social.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<PostDTO> obtenerFeed() {
        List<Post> posts = postRepository.findAllByOrderByFechaCreacionDesc();
        return posts.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public PostDTO crearPost(NuevoPostDTO dto) {
        Post post = new Post();
        post.setUserId(dto.getUserId());
        post.setUsuarioNombre(dto.getUsuarioNombre());
        post.setUsuarioAvatar("https://ui-avatars.com/api/?name=" + dto.getUsuarioNombre() + "&background=random");

        post.setLibroTitulo(dto.getLibroTitulo());
        post.setLibroAutor(dto.getLibroAutor());
        post.setLibroPortada(dto.getLibroPortada());
        post.setAccion(dto.getAccion());


        Post postGuardado = postRepository.save(post);

        if (dto.getComentario() != null && !dto.getComentario().isEmpty()) {
            agregarComentario(postGuardado.getId(), dto.getUsuarioNombre(), dto.getComentario());
        }

        return convertirADTO(postGuardado);
    }

    // --- NUEVAS FUNCIONALIDADES ---

    public PostDTO darLike(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        post.setLikesCount(post.getLikesCount() + 1);
        Post postGuardado = postRepository.save(post);
        return convertirADTO(postGuardado);
    }

    public void agregarComentario(Long postId, String usuario, String texto) {
        Comentario comentario = new Comentario();
        comentario.setPostId(postId);
        comentario.setUsuarioNombre(usuario);
        comentario.setTexto(texto);
        comentarioRepository.save(comentario);
    }

    // --- CONVERTIDOR ACTUALIZADO ---

    private PostDTO convertirADTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setUsuario(post.getUsuarioNombre());
        dto.setAvatar(post.getUsuarioAvatar());
        dto.setAccion(post.getAccion());
        dto.setLikes(post.getLikesCount()); // Mapeamos likes

        if (post.getFechaCreacion() != null) {
            dto.setTiempo(post.getFechaCreacion().format(DateTimeFormatter.ofPattern("dd/MM HH:mm")));
        }

        // Buscar comentarios de este post
        List<Comentario> comentarios = comentarioRepository.findByPostId(post.getId());
        List<ComentarioDTO> comentariosDTO = comentarios.stream().map(c -> {
            ComentarioDTO cdto = new ComentarioDTO();
            cdto.setId(c.getId());
            cdto.setUsuario(c.getUsuarioNombre());
            cdto.setTexto(c.getTexto());
            // Formatear fecha comentario
            if(c.getFecha() != null) {
                cdto.setTiempo(c.getFecha().format(DateTimeFormatter.ofPattern("HH:mm")));
            }
            return cdto;
        }).collect(Collectors.toList());

        dto.setComentarios(comentariosDTO);

        PostDTO.LibroInfo libroInfo = new PostDTO.LibroInfo();
        libroInfo.setTitulo(post.getLibroTitulo());
        libroInfo.setAutor(post.getLibroAutor());
        libroInfo.setPortada(post.getLibroPortada());
        dto.setLibro(libroInfo);

        return dto;
    }
}