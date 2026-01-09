package org.book_store.booklibraryapi.exception;

public class BorrowRecordNotFoundException extends RuntimeException {
    public BorrowRecordNotFoundException(String message) {
        super(message);
    }
}
