package org.book_store.booklibraryapi.exception;

public class BookAuthorNotFoundException extends RuntimeException {
    public BookAuthorNotFoundException(String message) {
        super(message);
    }
}
