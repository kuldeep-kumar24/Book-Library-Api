package org.book_store.booklibraryapi.repository;

import org.book_store.booklibraryapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {

}
