package org.book_store.booklibraryapi.service;

import org.book_store.booklibraryapi.exception.BorrowRecordBookNotFound;
import org.book_store.booklibraryapi.exception.BorrowRecordMemberNotFound;
import org.book_store.booklibraryapi.exception.BorrowRecordNotFoundException;
import org.book_store.booklibraryapi.model.Book;
import org.book_store.booklibraryapi.model.BorrowRecord;
import org.book_store.booklibraryapi.model.Member;
import org.book_store.booklibraryapi.repository.BookRepository;
import org.book_store.booklibraryapi.repository.BorrowRecordRepository;
import org.book_store.booklibraryapi.repository.MemberRepository;
import org.book_store.booklibraryapi.requestDTO.BorrowRecordRequestDTO;
import org.book_store.booklibraryapi.responseDTO.BookReturnResponseDTO;
import org.book_store.booklibraryapi.responseDTO.BorrowRecordResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowRecordService implements BorrowRecordServiceInterface{

    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository,BookRepository bookRepository,MemberRepository memberRepository) {
        this.borrowRecordRepository=borrowRecordRepository;
        this.bookRepository=bookRepository;
        this.memberRepository=memberRepository;
    }

    private BorrowRecordResponseDTO mapToBorrowRecordResponseDTO(BorrowRecord borrowRecord){
        return new BorrowRecordResponseDTO(borrowRecord.getBorrowRecordId(),borrowRecord.getBook().getTitle(),borrowRecord.getMember().getName(),borrowRecord.getBorrowDate(),borrowRecord.getReturnDate()!=null);
    }

    private BookReturnResponseDTO mapToBookReturnResponseDTO(BorrowRecord borrowRecord){
        return new BookReturnResponseDTO(borrowRecord.getBorrowRecordId(),borrowRecord.getBook().getTitle(),borrowRecord.getMember().getName(),borrowRecord.getBorrowDate(),borrowRecord.getReturnDate());
    }
    
    @Override
    public BorrowRecordResponseDTO add(BorrowRecordRequestDTO borrowRecordRequestDTO) {
        Member member=memberRepository.findById(borrowRecordRequestDTO.getMemberId()).orElseThrow(() -> new BorrowRecordMemberNotFound("Member id "+borrowRecordRequestDTO.getMemberId()+" does not exist"));
        Book book=bookRepository.findById(borrowRecordRequestDTO.getBookId()).orElseThrow(() -> new BorrowRecordBookNotFound("Book id "+borrowRecordRequestDTO.getBookId()+" does not exist"));
        BorrowRecord record=new BorrowRecord();
        record.setBook(book);
        record.setMember(member);
        record.setBorrowDate(LocalDate.now());
        BorrowRecord savedRecord=borrowRecordRepository.save(record);
        return mapToBorrowRecordResponseDTO(savedRecord);
    }

    @Override
    public List<BorrowRecordResponseDTO> getRecords() {
        List<BorrowRecord> borrowRecords=borrowRecordRepository.findAll();
        List<BorrowRecordResponseDTO> borrowRecordResponseDTOs=new ArrayList<>();
        for(BorrowRecord borrowRecord:borrowRecords){
            borrowRecordResponseDTOs.add(mapToBorrowRecordResponseDTO(borrowRecord));
        }
        return borrowRecordResponseDTOs;
    }

    @Override
    public BorrowRecordResponseDTO getRecord(Long id) {
        BorrowRecord record=borrowRecordRepository.findById(id).orElseThrow(() -> new BorrowRecordNotFoundException("Borrow record id "+id+" does not found"));
        return mapToBorrowRecordResponseDTO(record);
    }

    @Override
    public BorrowRecordResponseDTO updateRecord(Long id, BorrowRecordRequestDTO borrowRecordRequestDTO) {
        BorrowRecord record=borrowRecordRepository.findById(id).orElseThrow(() -> new BorrowRecordNotFoundException("Borrow record id "+id+" does not found"));
        Member member=memberRepository.findById(borrowRecordRequestDTO.getMemberId()).orElseThrow(() -> new BorrowRecordMemberNotFound("Member id "+borrowRecordRequestDTO.getMemberId()+" does not exist"));
        Book book=bookRepository.findById(borrowRecordRequestDTO.getBookId()).orElseThrow(() -> new BorrowRecordBookNotFound("Book id "+borrowRecordRequestDTO.getBookId()+" does not exist"));
        record.setBook(book);
        record.setMember(member);
        record.setBorrowDate(LocalDate.now());
        BorrowRecord updatedRecord=borrowRecordRepository.save(record);
        return mapToBorrowRecordResponseDTO(updatedRecord);
    }

    @Override
    public BorrowRecordResponseDTO editRecord(Long id, BorrowRecordRequestDTO borrowRecordRequestDTO) {
        BorrowRecord record=borrowRecordRepository.findById(id).orElseThrow(() -> new BorrowRecordNotFoundException("Borrow record id "+id+" does not found"));
        if(borrowRecordRequestDTO.getMemberId()!=null && !borrowRecordRequestDTO.getMemberId().equals(record.getMember().getMemberId()))
            record.setMember(memberRepository.findById(borrowRecordRequestDTO.getMemberId()).orElseThrow(() -> new BorrowRecordMemberNotFound("Member id "+borrowRecordRequestDTO.getMemberId()+" does not exist")));
        if(borrowRecordRequestDTO.getBookId()!=null && !borrowRecordRequestDTO.getBookId().equals(record.getBook().getBookId()))
            record.setBook(bookRepository.findById(borrowRecordRequestDTO.getBookId()).orElseThrow(() -> new BorrowRecordBookNotFound("Book id "+borrowRecordRequestDTO.getBookId()+" does not exist")));
        BorrowRecord updatedRecord=borrowRecordRepository.save(record);
        return mapToBorrowRecordResponseDTO(updatedRecord);
    }

    @Override
    public void deleteBorrowRecord(Long id) {
        if(!borrowRecordRepository.existsById(id))
            throw new BorrowRecordNotFoundException("Borrow record id "+id+" does not exist");
        borrowRecordRepository.deleteById(id);
    }

    @Override
    public BookReturnResponseDTO returnBook(Long id) {
        BorrowRecord record=borrowRecordRepository.findById(id).orElseThrow(() -> new BorrowRecordNotFoundException("Borrow record id "+id+" does not Found"));
        record.setReturnDate(LocalDate.now());
        BorrowRecord updatedRecord=borrowRecordRepository.save(record);
        return mapToBookReturnResponseDTO(updatedRecord);
    }
}
