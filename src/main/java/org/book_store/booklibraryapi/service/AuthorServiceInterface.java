package org.book_store.booklibraryapi.service;

import org.book_store.booklibraryapi.model.Author;

import java.util.List;

public interface AuthorServiceInterface {
    Author add(Author author);

    List<Author> getAuthorList();

    Author getById(Long id);

    Author updateAuthor(Long id, Author author);

    void deleteAuthorById(Long id);
}
