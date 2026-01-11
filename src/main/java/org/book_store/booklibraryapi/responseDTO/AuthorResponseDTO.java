package org.book_store.booklibraryapi.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthorResponseDTO {

    private Long authorId;
    private String name;
    private String email;

}
