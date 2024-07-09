package com.example.hello.repository;

import com.example.hello.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


 // 동시성 문제란? 여러 사용자가 동시에 하나의 대상에 요청을 할때, 시스템이 어떤 순서로 이를 처리할지 결정해야하는 상황을 말한다.
 // 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
//@Repository
public class MemoryMemberRepository implements MemberRepository {
//    repository에서 데이터를 저장하고 관리

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //실무에서는 동시성 문제를 고려해서 AtomicLong을 사용해야함

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable((store.get(id)));
    }

    @Override
    public Optional<Member> findByName(String name) {
//        람다식 - 메서드를 하나의 식으로 표현한것. 메서드를 람다식으로 표현하면 메서드의 이름과 반환값이 없어지므로 익명함수라고도 한다.
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
