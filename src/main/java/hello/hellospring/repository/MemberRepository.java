package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //추상메소드

    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);



    List<Member> findAll();     //저장된 모든 객체 반환
}
