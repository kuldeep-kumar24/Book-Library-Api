package org.book_store.booklibraryapi.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class MemberResponseDTO {

    private Long memberId;
    private String name;
    private String email;
    private String address;
    private Long phoneNumber;
    private Date membershipDate;
}
