package org.book_store.booklibraryapi.repository;

import org.book_store.booklibraryapi.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

}
