package Booksly.social.controller;


import Booksly.social.dto.ComentarioDTO;
import Booksly.social.dto.NuevoPostDTO;
import Booksly.social.dto.PostDTO;
import Booksly.social.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/social")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/feed")
    public List<PostDTO> obtenerFeed() {
        return postService.obtenerFeed();
    }

    @PostMapping("/post")
    public PostDTO crearPost(@RequestBody NuevoPostDTO nuevoPost) {
        return postService.crearPost(nuevoPost);
    }


    @PostMapping("/post/{id}/like")
    public PostDTO darLike(@PathVariable Long id) {
        return postService.darLike(id);
    }

    @PostMapping("/post/{id}/comentar")
    public void comentar(@PathVariable Long id, @RequestBody ComentarioDTO comentario) {
        postService.agregarComentario(id, comentario.getUsuario(), comentario.getTexto());
    }
}