package com.example.ecommerce_api.controllers;

import com.example.ecommerce_api.domain.post.Post;
import com.example.ecommerce_api.domain.user.User;
import com.example.ecommerce_api.dto.post.CreatePostDTO;
import com.example.ecommerce_api.dto.post.ResponsePostDTO;
import com.example.ecommerce_api.repositories.PostRepository;
import com.example.ecommerce_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostRepository repository;
    private final UserRepository userRepository;

    @PostMapping()
    public ResponseEntity create(@RequestBody CreatePostDTO body){
        User author = userRepository.findById(body.idAuthor()).orElse(null);
        if (author == null)
            return ResponseEntity.badRequest().body("Ocorreu um erro inesperado!");

        Post newPost = new Post();
        newPost.setTitle(body.title());
        newPost.setSubtitle(body.subtitle());
        newPost.setText(body.text());
        newPost.setAuthor(author);
        this.repository.save(newPost);

        return ResponseEntity.ok("Postagem criada com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable String id){
        Post post = this.repository.findById(id).orElse(null);
        if (post == null)
            return ResponseEntity.badRequest().body("Postagem não encontrada!");

        return ResponseEntity.ok(new ResponsePostDTO(post.getTitle(), post.getSubtitle(), post.getText()));
    }
}
