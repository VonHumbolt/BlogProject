package com.kaankaplan.blog_app.api.controllers;

import com.kaankaplan.blog_app.business.abstracts.AuthorService;
import com.kaankaplan.blog_app.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/authors/")
@CrossOrigin
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("getall")
    public ResponseEntity<List<Author>> getAllAuthors(@RequestParam Optional<Integer> pageNo, @RequestParam Optional<Integer> pageSize) {

        List<Author> authors = this.authorService.getAllAuthors(pageNo.orElse(1), pageNo.orElse(10));

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
    @GetMapping("getById/{authorId}")
    public ResponseEntity<Author> getAuthorById(@PathVariable int authorId) {

        Author author = this.authorService.getAuthorById(authorId);

        return new ResponseEntity<>(author, HttpStatus.OK);
    }
    @PostMapping ("delete/{authorId}")
    public ResponseEntity<String> delete(@PathVariable int authorId) {

        this.authorService.delete(authorId);

        return new ResponseEntity<>("Silindi", HttpStatus.OK);
    }


}
