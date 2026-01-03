package org.book_store.booklibraryapi.service;

import org.book_store.booklibraryapi.requestDTO.MemberRequestDTO;
import org.book_store.booklibraryapi.responseDTO.MemberResponseDTO;

import java.util.List;

public interface MemberServiceInterface {
    MemberResponseDTO add(MemberRequestDTO memberRequestDTO);

    List<MemberResponseDTO> getMembers();

    MemberResponseDTO getMember(Long id);

    MemberResponseDTO updateMember(Long id, MemberRequestDTO memberRequestDTO);

    MemberResponseDTO patchUpdateMember(Long id, MemberRequestDTO memberRequestDTO);

    void deleteMember(Long id);
}
