package org.book_store.booklibraryapi.exception;

public class BorrowRecordMemberNotFound extends RuntimeException {
    public BorrowRecordMemberNotFound(String message) {
        super(message);
    }
}
