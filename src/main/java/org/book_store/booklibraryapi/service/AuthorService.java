package org.book_store.booklibraryapi.service;

import org.book_store.booklibraryapi.exception.AuthorNotFoundException;
import org.book_store.booklibraryapi.model.Author;
import org.book_store.booklibraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class AuthorService implements AuthorServiceInterface{

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository){
        this.repository=repository;
    }

    @Override
    public Author add(Author author) {
       return repository.save(author);
    }

    @Override
    public List<Author> getAuthorList() {
        return repository.findAll();
    }

    @Override
    public Author getById(Long id) {
        return repository.findById(id).orElseThrow(()->new AuthorNotFoundException("Author "+id+" not found"));
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        if(!repository.existsById(id))
            throw new AuthorNotFoundException("Author "+id+" not found");
        author.setAuthorId(id);
        return repository.save(author);
    }

    @Override
    public void deleteAuthorById(Long id) {
        Author author=repository.findById(id).orElseThrow(()->new AuthorNotFoundException("Author "+id+" not found"));
        repository.delete(author);
    }
}
