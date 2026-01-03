package org.book_store.booklibraryapi.controller;

import org.book_store.booklibraryapi.requestDTO.MemberRequestDTO;
import org.book_store.booklibraryapi.responseDTO.MemberResponseDTO;
import org.book_store.booklibraryapi.service.MemberServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberServiceInterface service;

    public MemberController(MemberServiceInterface service){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<MemberResponseDTO> addMember(@RequestBody MemberRequestDTO memberRequestDTO){
        MemberResponseDTO savedMember=service.add(memberRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDTO>> getAllMembers(){
        List<MemberResponseDTO> members=service.getMembers();
        if(members.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> getMemberById(@PathVariable Long id){
        MemberResponseDTO member=service.getMember(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(member);
    }

    @PostMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> updateMember(@PathVariable Long id, @RequestBody MemberRequestDTO memberRequestDTO){
        MemberResponseDTO memberResponseDTO=service.updateMember(id,memberRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> patchMember(@PathVariable Long id, @RequestBody MemberRequestDTO memberRequestDTO){
        MemberResponseDTO memberResponseDTO=service.patchUpdateMember(id,memberRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id){
        service.deleteMember(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
