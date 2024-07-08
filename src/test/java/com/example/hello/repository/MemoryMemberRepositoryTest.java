package com.example.hello.repository;

import com.example.hello.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*; // static import 하는 것 그러면 앞으로 Assertions. 안써도 됨

// 테스트를 먼저 만들고 구현클래스를 만드는것 (테스트 주도 개발 TDD)
//테스트는 순서가 보장이 안되어, 모든 메서드는 순서에 의존적이게 설계하면 안됌!!!!
//굳이 public 안붙혀도 됨. 딴곳에서 갖다 쓰지 않을것이기 때문.
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

//    순서와 관계없이 하기위해 한개의 테스트가 끝날 때 clear시키게 메서드
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

   @Test
    public void save() {
       Member member = new Member();
       member.setName("spring");

       repository.save(member);

       Member result = repository.findById(member.getId()).get();
//       Assertions.assertEquals(result,member); // jupiter
       assertThat(member).isEqualTo(result);
   }

   @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

   }

   @Test
    public void findAll() {
       Member member1 = new Member();
       member1.setName("spring1");
       repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

       List<Member> result = repository.findAll();

       assertThat(result.size()).isEqualTo(2);
   }

}
