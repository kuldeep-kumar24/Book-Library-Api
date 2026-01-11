package org.book_store.booklibraryapi.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthorRequestDTO {

    private String firstName;
    private String lastName;
    private String email;

}
