package org.book_store.booklibraryapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<?> handlerAuthorNotFoundException(AuthorNotFoundException authorNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Massage: "+authorNotFoundException.getMessage()+"\nStates Code: "+HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handlerBookNotFoundException(BookNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Massage: "+ex.getMessage()+"\nStates Code: "+HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookAuthorNotFoundException.class)
    public ResponseEntity<?> handlerBookAuthorNotFoundException(BookAuthorNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Massage: "+ex.getMessage()+"\nStates Code: "+HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<?> handlerMemberNotFoundException(MemberNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Massage: "+ex.getMessage()+"\nStates Code: "+HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BorrowRecordMemberNotFound.class)
    public ResponseEntity<?> handlerBorrowRecordMemberNotFound(BorrowRecordMemberNotFound ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Massage: "+ex.getMessage()+"\nStates Code: "+HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BorrowRecordBookNotFound.class)
    public ResponseEntity<?> handlerBorrowRecordBookNotFound(BorrowRecordBookNotFound ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Massage: "+ex.getMessage()+"\nStates Code: "+HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BorrowRecordNotFoundException.class)
    public ResponseEntity<?> handlerBorrowRecordNotFound(BorrowRecordNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Massage: "+ex.getMessage()+"\nStates Code: "+HttpStatus.NOT_FOUND);
    }
}
