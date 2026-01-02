package org.book_store.booklibraryapi.service;

import org.book_store.booklibraryapi.model.Book;
import org.book_store.booklibraryapi.requestDTO.BookRequestDTO;
import org.book_store.booklibraryapi.responseDTO.BookResponseDTO;

import java.util.List;

public interface BookServiceInterface {
    BookResponseDTO add(BookRequestDTO book);

    List<BookResponseDTO> getBooks();

    BookResponseDTO getBook(Long id);

    BookResponseDTO update(Long id, BookRequestDTO book);

    BookResponseDTO updateById(Long id, BookRequestDTO book);

    void delete(Long id);
}
