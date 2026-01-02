package org.book_store.booklibraryapi.requestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestDTO {

    private String title;
    private String isbn;
    private int publishedYear;
    private Long authorID;

}
