package org.book_store.booklibraryapi.service;

import org.book_store.booklibraryapi.exception.BookAuthorNotFoundException;
import org.book_store.booklibraryapi.exception.BookNotFoundException;
import org.book_store.booklibraryapi.model.Author;
import org.book_store.booklibraryapi.model.Book;
import org.book_store.booklibraryapi.repository.AuthorRepository;
import org.book_store.booklibraryapi.repository.BookRepository;
import org.book_store.booklibraryapi.requestDTO.BookRequestDTO;
import org.book_store.booklibraryapi.responseDTO.BookResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements BookServiceInterface{

    private final BookRepository repository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository repository,AuthorRepository authorRepository){
        this.repository=repository;
        this.authorRepository=authorRepository;
    }

    public BookResponseDTO mapToBookResponseDTO(Book book){
        return new BookResponseDTO(book.getBookId(),book.getTitle(),book.getIsbn(),book.getPublishedYear(),book.getAuthor().getName());
    }

    @Override
    public BookResponseDTO add(BookRequestDTO book) {
        Author author=authorRepository.findById(book.getAuthorID()).orElseThrow(()-> new BookAuthorNotFoundException("Author ID " + book.getAuthorID() + " does not exist"));

        Book newBook=new Book();
        newBook.setTitle(book.getTitle());
        newBook.setIsbn(book.getIsbn());
        newBook.setPublishedYear(book.getPublishedYear());
        newBook.setAuthor(author);

        Book savedBook=repository.save(newBook);

        return mapToBookResponseDTO(savedBook);
    }

    @Override
    public List<BookResponseDTO> getBooks() {
        List<Book> books=repository.findAll();
        List<BookResponseDTO> bookResponseDTOS=new ArrayList<>();
        for(Book book:books){
            bookResponseDTOS.add(mapToBookResponseDTO(book));
        }
        return bookResponseDTOS;
    }

    @Override
    public BookResponseDTO getBook(Long id) {
        Book book=repository.findById(id).orElseThrow(() -> new BookNotFoundException("Book "+id+" does not found"));
        return mapToBookResponseDTO(book);
    }

    @Override
    public BookResponseDTO update(Long id, BookRequestDTO book) {
        Book newBook=repository.findById(id).orElseThrow(() -> new BookNotFoundException("Book "+id+" does not found"));

        Author author=authorRepository.findById(book.getAuthorID()).orElseThrow(() -> new BookAuthorNotFoundException("Author ID " + book.getAuthorID() + " does not exist"));

        newBook.setTitle(book.getTitle());
        newBook.setIsbn(book.getIsbn());
        newBook.setPublishedYear(book.getPublishedYear());
        newBook.setAuthor(author);

        Book updatedBook=repository.save(newBook);

        return mapToBookResponseDTO(updatedBook);
    }

    @Override
    public BookResponseDTO updateById(Long id, BookRequestDTO book) {
        Book newBook=repository.findById(id).orElseThrow(() -> new BookNotFoundException("Book "+id+" does not found"));
        if(book.getAuthorID()!=null && !newBook.getAuthor().getAuthorId().equals(book.getAuthorID()))
            newBook.setAuthor(authorRepository.findById(book.getAuthorID()).orElseThrow(() -> new BookAuthorNotFoundException("Author ID " + book.getAuthorID() + " does not exist")));
        if(book.getTitle()!=null && !newBook.getTitle().equals(book.getTitle()))
            newBook.setTitle(book.getTitle());
        if(book.getIsbn()!=null && !newBook.getIsbn().equals(book.getIsbn()))
            newBook.setIsbn(book.getIsbn());
        if(book.getPublishedYear()!=0 && newBook.getPublishedYear()!=book.getPublishedYear())
            newBook.setPublishedYear(book.getPublishedYear());

        Book updatedBook=repository.save(newBook);

        return mapToBookResponseDTO(updatedBook);
    }

    @Override
    public void delete(Long id) {
        Book book=repository.findById(id).orElseThrow(() -> new BookNotFoundException("Book "+id+" does not found"));
        repository.delete(book);
    }


}
