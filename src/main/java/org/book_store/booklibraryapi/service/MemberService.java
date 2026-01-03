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

    @Override
    public MemberResponseDTO add(MemberRequestDTO memberRequestDTO) {
        Member member = new Member();
        member.setName(memberRequestDTO.getFirstName()+" "+memberRequestDTO.getLastName());
        member.setEmail(memberRequestDTO.getEmail());
        member.setAddress(memberRequestDTO.getAddress());
        member.setPhoneNumber(memberRequestDTO.getPhoneNumber());
        member.setMembershipDate(memberRequestDTO.getMembershipDate());

        Member savedMember = repository.save(member);
        return new MemberResponseDTO(savedMember.getMemberId(),savedMember.getName(), savedMember.getEmail(), savedMember.getAddress(), savedMember.getPhoneNumber(), savedMember.getMembershipDate());
    }

    @Override
    public List<MemberResponseDTO> getMembers() {
        List<Member> members=repository.findAll();
        List<MemberResponseDTO> memberResponseDTOS=new ArrayList<>();
        for(Member member:members){
         memberResponseDTOS.add(new MemberResponseDTO(member.getMemberId(),member.getName(),member.getEmail(),member.getAddress(),member.getPhoneNumber(),member.getMembershipDate()));
        }
        return memberResponseDTOS;
    }

    @Override
    public MemberResponseDTO getMember(Long id) {
        Member member=repository.findById(id).orElseThrow(() -> new MemberNotFoundException("Member Id "+id+" Not Found"));
        return new MemberResponseDTO(member.getMemberId(), member.getName(), member.getEmail(), member.getAddress(), member.getPhoneNumber(), member.getMembershipDate());
    }

    @Override
    public MemberResponseDTO updateMember(Long id, MemberRequestDTO memberRequestDTO) {
        if(!repository.existsById(id))
            throw new MemberNotFoundException("Member Id "+id+" Not Found");

        Member member=new Member();
        member.setMemberId(id);
        member.setName(memberRequestDTO.getFirstName()+" "+memberRequestDTO.getLastName());
        member.setEmail(memberRequestDTO.getEmail());
        member.setAddress(memberRequestDTO.getAddress());
        member.setPhoneNumber(memberRequestDTO.getPhoneNumber());
        member.setMembershipDate(memberRequestDTO.getMembershipDate());

        Member savedMember = repository.save(member);

        return new MemberResponseDTO(savedMember.getMemberId(), savedMember.getName(), savedMember.getEmail(), savedMember.getAddress(), savedMember.getPhoneNumber(), savedMember.getMembershipDate());
    }

    @Override
    public MemberResponseDTO patchUpdateMember(Long id, MemberRequestDTO memberRequestDTO) {
        Member member=repository.findById(id).orElseThrow(() -> new MemberNotFoundException("Member Id "+id+" Not Found"));

        if(!member.getName().equals(memberRequestDTO.getFirstName()+" "+memberRequestDTO.getLastName()) && memberRequestDTO.getFirstName()!=null && memberRequestDTO.getLastName()!=null){
            member.setName(memberRequestDTO.getFirstName()+" "+memberRequestDTO.getLastName());
        }
        if (!member.getEmail().equals(memberRequestDTO.getEmail()) && memberRequestDTO.getEmail() != null) {
            member.setEmail(memberRequestDTO.getEmail());
        }
        if(!member.getAddress().equals(memberRequestDTO.getAddress()) && memberRequestDTO.getAddress() != null) {
            member.setAddress(memberRequestDTO.getAddress());
        }
        if (!member.getPhoneNumber().equals(memberRequestDTO.getPhoneNumber()) && memberRequestDTO.getPhoneNumber() != null) {
            member.setPhoneNumber(memberRequestDTO.getPhoneNumber());
        }
        if (!member.getMembershipDate().equals(memberRequestDTO.getMembershipDate()) && memberRequestDTO.getMembershipDate() != null) {
            member.setMembershipDate(memberRequestDTO.getMembershipDate());
        }
        Member updatedMember = repository.save(member);
        return new MemberResponseDTO(updatedMember.getMemberId(),updatedMember.getName(),updatedMember.getEmail(),updatedMember.getAddress(),updatedMember.getPhoneNumber(),updatedMember.getMembershipDate());
    }

    @Override
    public void deleteMember(Long id) {
        if(!repository.existsById(id))
            throw new MemberNotFoundException("Member Id "+id+" Not Found");
        repository.deleteById(id);
    }
}
