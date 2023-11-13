package com.post.test.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.post.test.entity.Post;
import com.post.test.repository.PostRepository;

@Service
public class PostService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);
    private static final String POSTS_API_URL = "https://jsonplaceholder.typicode.com/posts";

    @Autowired
    private PostRepository postRepository;

    // Ottiene tutti i post dal database locale.   
    // ritorna una Lista di post presenti nel database.
    public List<Post> getAllPosts() {
        try {
            LOGGER.info("DATI ELABORATI CON SUCCESSO");
            return postRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Errore durante il caricamento dei post.", e);
            // In caso di errore, restituisce una lista vuota con un messaggio di errore.
            List<Post> emptyList = Collections.emptyList();
            emptyList.add(new Post(null, null, "Errore durante il caricamento dei post.", null));
            return emptyList;
        }
    }


    // Recupera i post da JSONplaceholder e li salva nel database locale.
    public void retrieveAndSavePosts() {
        try {
            // Effettua la chiamata REST per recuperare i post
            RestTemplate restTemplate = new RestTemplate();
            Post[] posts = restTemplate.getForObject(POSTS_API_URL, Post[].class);

            // Salva i post nel database
            if (posts != null) {
                for (Post post : posts) {
                    postRepository.save(post);
                }
                LOGGER.info("DATI RECUPERATI E SALVATI CON SUCCESSO NEL DB");
            } else {
                LOGGER.warn("Nessun dato recuperato dalla chiamata API.");
            }
        } catch (Exception e) {
            LOGGER.error("Errore durante la chiamata API o il salvataggio dei post.", e);
        }
    }
}
