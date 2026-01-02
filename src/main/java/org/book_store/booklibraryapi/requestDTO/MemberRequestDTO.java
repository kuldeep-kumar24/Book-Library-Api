package org.book_store.booklibraryapi.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class MemberRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private String address;
    private Date membershipDate;

}
