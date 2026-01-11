package org.book_store.booklibraryapi.controller;

import org.book_store.booklibraryapi.model.Author;
import org.book_store.booklibraryapi.service.AuthorServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorServiceInterface service;

    public AuthorController(AuthorServiceInterface service){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author){
        Author saved=service.add(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAuthor(){
        List<Author> authors=service.getAuthorList();
        if(authors.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
        Author author=service.getById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author,@PathVariable Long id){
        Author updatedAuthor=service.updateAuthor(id,author);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        service.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }
}
