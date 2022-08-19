package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); //static: 프로그램이 실행될 때 실행 됨. 모든 인스턴스가 공동으로 사용하기위해
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member; //객체반환, 새로운 멤버를 생성시켜 저장소에 등록
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //아이디의 객체 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //맵을 활용한 함수
                .filter(member -> member.getName().equals(name)) //중간연산
                .findAny(); //최종연산 (1번만 수행)
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearstore() {
        store.clear();
    }

}


