package org.book_store.booklibraryapi.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BookReturnResponseDTO {

    private Long BorrowRecordId;
    private String bookName;
    private String memberName;
    private LocalDate borrowDate;
    private LocalDate returnedDate;

}
