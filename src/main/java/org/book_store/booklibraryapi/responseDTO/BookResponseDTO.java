package org.book_store.booklibraryapi.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.book_store.booklibraryapi.model.Author;

@Getter
@Setter
@AllArgsConstructor
public class BookResponseDTO {

    private Long bookId;
    private String title;
    private String isbn;
    private int publishedYear;
    private String authorName;
}
