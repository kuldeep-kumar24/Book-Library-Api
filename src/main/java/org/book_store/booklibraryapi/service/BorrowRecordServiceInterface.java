package org.book_store.booklibraryapi.service;

import org.book_store.booklibraryapi.requestDTO.BorrowRecordRequestDTO;
import org.book_store.booklibraryapi.responseDTO.BorrowRecordResponseDTO;

import java.util.List;

public interface BorrowRecordServiceInterface {

    BorrowRecordResponseDTO add(BorrowRecordRequestDTO borrowRecordRequestDTO);

    List<BorrowRecordResponseDTO> getRecords();

    BorrowRecordResponseDTO getRecord(Long id);

    BorrowRecordResponseDTO updateRecord(Long id, BorrowRecordRequestDTO borrowRecordRequestDTO);

    BorrowRecordResponseDTO editRecord(Long id, BorrowRecordRequestDTO borrowRecordRequestDTO);

    void deleteBorrowRecord(Long id);
}
