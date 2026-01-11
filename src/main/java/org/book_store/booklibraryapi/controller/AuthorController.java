package org.book_store.booklibraryapi.controller;

import org.book_store.booklibraryapi.model.Author;
import org.book_store.booklibraryapi.requestDTO.AuthorRequestDTO;
import org.book_store.booklibraryapi.responseDTO.AuthorResponseDTO;
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
    public ResponseEntity<AuthorResponseDTO> addAuthor(@RequestBody AuthorRequestDTO author){
        AuthorResponseDTO saved=service.add(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAuthor(){
        List<AuthorResponseDTO> authors=service.getAuthorList();
        if(authors.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id){
        AuthorResponseDTO author=service.getById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@RequestBody AuthorRequestDTO author,@PathVariable Long id){
        AuthorResponseDTO updatedAuthor=service.updateAuthor(id,author);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        service.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable Long id,@RequestBody AuthorRequestDTO author){
        AuthorResponseDTO updatedAuthor=service.edit(id,author);
        return ResponseEntity.ok(updatedAuthor);
    }
}
