package org.book_store.booklibraryapi.repository;

import org.book_store.booklibraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

}
