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

    @Override
    public BookResponseDTO add(BookRequestDTO book) {
        Author author=authorRepository.findById(book.getAuthorID()).orElseThrow(()-> new BookAuthorNotFoundException("Author ID " + book.getAuthorID() + " does not exist"));

        Book newBook=new Book();
        newBook.setTitle(book.getTitle());
        newBook.setIsbn(book.getIsbn());
        newBook.setPublishedYear(book.getPublishedYear());
        newBook.setAuthor(author);

        Book savedBook=repository.save(newBook);

        return new BookResponseDTO(savedBook.getBookId(),savedBook.getTitle(),savedBook.getIsbn(),savedBook.getPublishedYear(),savedBook.getAuthor().getName());
    }

    @Override
    public List<BookResponseDTO> getBooks() {
        List<Book> books=repository.findAll();
        List<BookResponseDTO> bookResponseDTOS=new ArrayList<>();
        for(Book book:books){
            bookResponseDTOS.add(new BookResponseDTO(book.getBookId(),book.getTitle(),book.getIsbn(),book.getPublishedYear(),book.getAuthor().getName()));
        }
        return bookResponseDTOS;
    }

    @Override
    public BookResponseDTO getBook(Long id) {
        Book book=repository.findById(id).orElseThrow(() -> new BookNotFoundException("Book "+id+" does not found"));
        return new BookResponseDTO(book.getBookId(),book.getTitle(),book.getIsbn(),book.getPublishedYear(),book.getAuthor().getName());
    }

    @Override
    public BookResponseDTO update(Long id, BookRequestDTO book) {
        if(!repository.existsById(id))
            throw new BookNotFoundException("Book "+id+" does not found");

        Author author=authorRepository.findById(book.getAuthorID()).orElseThrow(() -> new BookAuthorNotFoundException("Author ID " + book.getAuthorID() + " does not exist"));

        Book newBook=new Book();
        newBook.setBookId(id);
        newBook.setTitle(book.getTitle());
        newBook.setIsbn(book.getIsbn());
        newBook.setPublishedYear(book.getPublishedYear());
        newBook.setAuthor(author);

        Book updatedBook=repository.save(newBook);

        return new BookResponseDTO(updatedBook.getBookId(),updatedBook.getTitle(),updatedBook.getIsbn(),updatedBook.getPublishedYear(),updatedBook.getAuthor().getName());
    }

    @Override
    public BookResponseDTO updateById(Long id, BookRequestDTO book) {
        Book newBook=repository.findById(id).orElseThrow(() -> new BookNotFoundException("Book "+id+" does not found"));
        if(!newBook.getAuthor().getAuthorId().equals(book.getAuthorID()) && book.getAuthorID()!=null)
            newBook.setAuthor(authorRepository.findById(book.getAuthorID()).orElseThrow(() -> new BookAuthorNotFoundException("Author ID " + book.getAuthorID() + " does not exist")));
        if(!newBook.getTitle().equals(book.getTitle()) && book.getTitle()!=null)
            newBook.setTitle(book.getTitle());
        if(!newBook.getIsbn().equals(book.getIsbn()) && book.getIsbn()!=null)
            newBook.setIsbn(book.getIsbn());
        if(newBook.getPublishedYear()!=book.getPublishedYear() && book.getPublishedYear()!=0)
            newBook.setPublishedYear(book.getPublishedYear());

        Book updatedBook=repository.save(newBook);

        return new BookResponseDTO(updatedBook.getBookId(),updatedBook.getTitle(),updatedBook.getIsbn(),updatedBook.getPublishedYear(),updatedBook.getAuthor().getName());
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id))
            throw new BookNotFoundException("Book "+id+" does not found");
        repository.deleteById(id);
    }


}
