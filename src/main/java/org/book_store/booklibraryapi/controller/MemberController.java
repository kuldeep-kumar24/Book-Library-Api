package org.book_store.booklibraryapi.controller;

import org.book_store.booklibraryapi.service.MemberServiceInterface;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private MemberServiceInterface service;

    public MemberController(MemberServiceInterface service){
        this.service=service;
    }
}
