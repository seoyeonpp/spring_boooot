package com.example.hello.repository;

import com.example.hello.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// interface 가 interface를 받을때는 extends라고함. 다중상속 가능
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>,MemberRepository {

//    spring data jpa 는 interface를 가지고 구현체를 알아서 만들어줌!

    // JPQL 로 번역되어서 이렇게 짜줌 select m from Member m where m.name = ?
    // 네이밍 규칙만으로 할수있음 And 나 Or 이런식으로
    @Override
    Optional<Member> findByName(String name);
}
