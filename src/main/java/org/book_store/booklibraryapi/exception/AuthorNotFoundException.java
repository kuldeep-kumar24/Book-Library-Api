package org.book_store.booklibraryapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AuthorNotFoundException extends RuntimeException{

    public AuthorNotFoundException(String msg){
        super(msg);
    }
}
