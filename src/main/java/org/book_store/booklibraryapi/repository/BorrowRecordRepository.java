package org.book_store.booklibraryapi.repository;

import org.book_store.booklibraryapi.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord,Long> {

}
