package org.book_store.booklibraryapi.service;

import org.book_store.booklibraryapi.model.Author;
import org.book_store.booklibraryapi.requestDTO.AuthorRequestDTO;
import org.book_store.booklibraryapi.responseDTO.AuthorResponseDTO;

import java.util.List;

public interface AuthorServiceInterface {
    AuthorResponseDTO add(AuthorRequestDTO author);

    List<AuthorResponseDTO> getAuthorList();

    AuthorResponseDTO getById(Long id);

    AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO author);

    void deleteAuthorById(Long id);

    AuthorResponseDTO edit(Long id, AuthorRequestDTO author);
}
