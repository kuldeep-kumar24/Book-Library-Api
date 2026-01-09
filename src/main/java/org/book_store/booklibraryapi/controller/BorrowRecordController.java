package org.book_store.booklibraryapi.controller;

import org.book_store.booklibraryapi.requestDTO.BorrowRecordRequestDTO;
import org.book_store.booklibraryapi.responseDTO.BorrowRecordResponseDTO;
import org.book_store.booklibraryapi.service.BorrowRecordServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowRecord")
public class BorrowRecordController {

   private final BorrowRecordServiceInterface service;

   public BorrowRecordController(BorrowRecordServiceInterface service) {
       this.service = service;
   }

   @PostMapping
   public ResponseEntity<BorrowRecordResponseDTO> addBorrowRecord(@RequestBody BorrowRecordRequestDTO borrowRecordRequestDTO) {
       BorrowRecordResponseDTO borrowRecord = service.add(borrowRecordRequestDTO);
       return ResponseEntity.status(HttpStatus.CREATED).body(borrowRecord);
   }

   @GetMapping
    public ResponseEntity<List<BorrowRecordResponseDTO>> getBorrowRecords(){
       List<BorrowRecordResponseDTO> borrowRecords=service.getRecords();
       if(borrowRecords==null)
           return ResponseEntity.noContent().build();
       return ResponseEntity.status(HttpStatus.OK).body(borrowRecords);
   }

   @GetMapping("/{id}")
    public ResponseEntity<BorrowRecordResponseDTO> getBorrowRecord(@PathVariable Long id){
       BorrowRecordResponseDTO record=service.getRecord(id);
       return ResponseEntity.status(HttpStatus.FOUND).body(record);
   }

   @PutMapping("/{id}")
    public ResponseEntity<BorrowRecordResponseDTO> updateBorrowRecord(@PathVariable Long id, @RequestBody BorrowRecordRequestDTO borrowRecordRequestDTO){
       BorrowRecordResponseDTO updatedRecord=service.updateRecord(id,borrowRecordRequestDTO);
       return ResponseEntity.status(HttpStatus.OK).body(updatedRecord);
   }

   @PatchMapping("/{id}")
    public ResponseEntity<BorrowRecordResponseDTO> editBorrowRecord(@PathVariable Long id, @RequestBody BorrowRecordRequestDTO borrowRecordRequestDTO){
       BorrowRecordResponseDTO updatedRecord=service.editRecord(id,borrowRecordRequestDTO);
       return ResponseEntity.status(HttpStatus.OK).body(updatedRecord);
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBorrowRecord(@PathVariable Long id){
       service.deleteBorrowRecord(id);
       return ResponseEntity.ok().build();
   }

   @PatchMapping("/return/{id}")
    public ResponseEntity<>
}
