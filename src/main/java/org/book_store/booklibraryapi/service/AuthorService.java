package org.book_store.booklibraryapi.service;

import org.book_store.booklibraryapi.exception.AuthorNotFoundException;
import org.book_store.booklibraryapi.model.Author;
import org.book_store.booklibraryapi.repository.AuthorRepository;
import org.book_store.booklibraryapi.requestDTO.AuthorRequestDTO;
import org.book_store.booklibraryapi.responseDTO.AuthorResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Service
public class AuthorService implements AuthorServiceInterface{

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository){
        this.repository=repository;
    }

    private AuthorResponseDTO mapAuthorResponseDTO(Author author){
        return new AuthorResponseDTO(author.getAuthorId(),author.getName(),author.getEmail());
    }

    @Override
    public AuthorResponseDTO add(AuthorRequestDTO authorRequestDTO) {
        Author author=new Author();
        author.setName(authorRequestDTO.getFirstName()+" "+authorRequestDTO.getLastName());
        author.setEmail(authorRequestDTO.getEmail());
       Author saved = repository.save(author);
       return mapAuthorResponseDTO(saved);
    }

    @Override
    public List<AuthorResponseDTO> getAuthorList() {
        List<Author> authors = repository.findAll();
        List<AuthorResponseDTO> authorResponseDTOS=new ArrayList<>();
        for (Author author : authors) {
            authorResponseDTOS.add(mapAuthorResponseDTO(author));
        }
        return authorResponseDTOS;
    }

    @Override
    public AuthorResponseDTO getById(Long id) {
        Author author = repository.findById(id).orElseThrow(()->new AuthorNotFoundException("Author "+id+" not found"));
        return mapAuthorResponseDTO(author);
    }

    @Override
    public AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO authorRequestDTO) {
        Author author = repository.findById(id).orElseThrow(()->new AuthorNotFoundException("Author "+id+" not found"));
        author.setName(authorRequestDTO.getFirstName()+" "+authorRequestDTO.getLastName());
        author.setEmail(authorRequestDTO.getEmail());
        Author updatedAuthor = repository.save(author);
        return mapAuthorResponseDTO(updatedAuthor);
    }

    @Override
    public void deleteAuthorById(Long id) {
        Author author=repository.findById(id).orElseThrow(()->new AuthorNotFoundException("Author "+id+" not found"));
        repository.delete(author);
    }

    @Override
    public AuthorResponseDTO edit(Long id, AuthorRequestDTO authorRequestDTO) {
        Author author=repository.findById(id).orElseThrow(()->new AuthorNotFoundException("Author "+id+" not found"));
        if(authorRequestDTO.getFirstName()!=null && authorRequestDTO.getLastName()!=null && !author.getName().equals(authorRequestDTO.getFirstName()+" "+authorRequestDTO.getLastName())){
            author.setName(authorRequestDTO.getFirstName()+" "+authorRequestDTO.getLastName());
        }
        if(authorRequestDTO.getEmail()!=null && !author.getEmail().equals(authorRequestDTO.getEmail())){
            author.setEmail(authorRequestDTO.getEmail());
        }
        Author updatedAuthor = repository.save(author);
        return mapAuthorResponseDTO(updatedAuthor);
    }
}
