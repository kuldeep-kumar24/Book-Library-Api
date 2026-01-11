package org.book_store.booklibraryapi.controller;

import org.book_store.booklibraryapi.requestDTO.BookRequestDTO;
import org.book_store.booklibraryapi.responseDTO.BookResponseDTO;
import org.book_store.booklibraryapi.service.BookServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookServiceInterface service;

    public BookController(BookServiceInterface service){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(@RequestBody BookRequestDTO book){
        BookResponseDTO addedBook=service.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedBook);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks(){
        List<BookResponseDTO> books=service.getBooks();
        if(books.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id){
        BookResponseDTO book=service.getBook(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id,@RequestBody BookRequestDTO book){
        BookResponseDTO updatedBook=service.update(id,book);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBookById(@PathVariable Long id,@RequestBody BookRequestDTO book){
        BookResponseDTO updatedBook=service.updateById(id,book);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
