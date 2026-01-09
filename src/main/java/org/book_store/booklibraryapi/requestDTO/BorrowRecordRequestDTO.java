package org.book_store.booklibraryapi.requestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowRecordRequestDTO {

private  Long bookId;
private Long memberId;

}
