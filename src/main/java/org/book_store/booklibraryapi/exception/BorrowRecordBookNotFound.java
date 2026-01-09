package org.book_store.booklibraryapi.exception;

public class BorrowRecordBookNotFound extends RuntimeException {
    public BorrowRecordBookNotFound(String message) {
        super(message);
    }
}
