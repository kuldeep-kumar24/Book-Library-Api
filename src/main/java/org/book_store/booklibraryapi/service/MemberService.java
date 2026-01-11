package org.book_store.booklibraryapi.service;

import org.book_store.booklibraryapi.exception.MemberNotFoundException;
import org.book_store.booklibraryapi.model.Member;
import org.book_store.booklibraryapi.repository.MemberRepository;
import org.book_store.booklibraryapi.requestDTO.MemberRequestDTO;
import org.book_store.booklibraryapi.responseDTO.MemberResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService implements MemberServiceInterface{

    private final MemberRepository repository;
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    private MemberResponseDTO mapToMemberResponseDTO(Member member){
        return new MemberResponseDTO(member.getMemberId(),member.getName(), member.getEmail(), member.getAddress(), member.getPhoneNumber(), member.getMembershipDate());
    }

    @Override
    public MemberResponseDTO add(MemberRequestDTO memberRequestDTO) {
        Member member = new Member();
        member.setName(memberRequestDTO.getFirstName()+" "+memberRequestDTO.getLastName());
        member.setEmail(memberRequestDTO.getEmail());
        member.setAddress(memberRequestDTO.getAddress());
        member.setPhoneNumber(memberRequestDTO.getPhoneNumber());
        member.setMembershipDate(memberRequestDTO.getMembershipDate());

        Member savedMember = repository.save(member);
        return mapToMemberResponseDTO(savedMember);
    }

    @Override
    public List<MemberResponseDTO> getMembers() {
        List<Member> members=repository.findAll();
        List<MemberResponseDTO> memberResponseDTOS=new ArrayList<>();
        for(Member member:members){
         memberResponseDTOS.add(mapToMemberResponseDTO(member));
        }
        return memberResponseDTOS;
    }

    @Override
    public MemberResponseDTO getMember(Long id) {
        Member member=repository.findById(id).orElseThrow(() -> new MemberNotFoundException("Member Id "+id+" Not Found"));
        return mapToMemberResponseDTO(member);
    }

    @Override
    public MemberResponseDTO updateMember(Long id, MemberRequestDTO memberRequestDTO) {
        Member member=repository.findById(id).orElseThrow(() -> new MemberNotFoundException("Member Id "+id+" Not Found"));

        member.setName(memberRequestDTO.getFirstName()+" "+memberRequestDTO.getLastName());
        member.setEmail(memberRequestDTO.getEmail());
        member.setAddress(memberRequestDTO.getAddress());
        member.setPhoneNumber(memberRequestDTO.getPhoneNumber());
        member.setMembershipDate(memberRequestDTO.getMembershipDate());

        Member savedMember = repository.save(member);

        return mapToMemberResponseDTO(savedMember);
    }

    @Override
    public MemberResponseDTO patchUpdateMember(Long id, MemberRequestDTO memberRequestDTO) {
        Member member=repository.findById(id).orElseThrow(() -> new MemberNotFoundException("Member Id "+id+" Not Found"));

        if(memberRequestDTO.getFirstName()!=null && memberRequestDTO.getLastName()!=null && !member.getName().equals(memberRequestDTO.getFirstName()+" "+memberRequestDTO.getLastName())){
            member.setName(memberRequestDTO.getFirstName()+" "+memberRequestDTO.getLastName());
        }
        if (memberRequestDTO.getEmail() != null && !member.getEmail().equals(memberRequestDTO.getEmail())) {
            member.setEmail(memberRequestDTO.getEmail());
        }
        if(memberRequestDTO.getAddress() != null && !member.getAddress().equals(memberRequestDTO.getAddress())) {
            member.setAddress(memberRequestDTO.getAddress());
        }
        if (memberRequestDTO.getPhoneNumber() != null && !member.getPhoneNumber().equals(memberRequestDTO.getPhoneNumber())) {
            member.setPhoneNumber(memberRequestDTO.getPhoneNumber());
        }
        if (memberRequestDTO.getMembershipDate() != null && !member.getMembershipDate().equals(memberRequestDTO.getMembershipDate())) {
            member.setMembershipDate(memberRequestDTO.getMembershipDate());
        }
        Member updatedMember = repository.save(member);
        return mapToMemberResponseDTO(updatedMember);
    }

    @Override
    public void deleteMember(Long id) {
        Member member=repository.findById(id).orElseThrow(() -> new MemberNotFoundException("Member Id "+id+" Not Found"));
        repository.delete(member);
    }
}
