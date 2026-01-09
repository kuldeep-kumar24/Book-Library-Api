package org.book_store.booklibraryapi.responseDTO;

import java.time.LocalDate;

public class BookReturnResponseDTO {

    private Long BorrowRecordId;
    private String bookName;
    private String memberName;
    private LocalDate borrowDate;
    private LocalDate returnedDate;

}
