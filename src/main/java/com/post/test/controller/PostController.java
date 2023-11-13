package com.post.test.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.post.test.entity.Post;
import com.post.test.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // Endpoint per recuperare e salvare i post.   
    @GetMapping("/save")
    public ResponseEntity<String> retrieveAndSavePosts() {
        try {
            postService.retrieveAndSavePosts();
            return ResponseEntity.ok("I post sono stati recuperati e salvati con successo!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore durante il recupero e il salvataggio dei post: " + e.getMessage());
        }
    }

    
    // Endpoint per recuperare tutti i post.  
    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts() {
        try {
            List<Post> posts = postService.getAllPosts();
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList()); // Restituisce una lista vuota in caso di errore
        }
    }
}
