package com.example.hello.repository;

import com.example.hello.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    };

    @Override
    public Member save(Member member) {
        em.persist(member); // 영구 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        System.out.println("result = " + result);

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
//        jpql 을 사용하여 객체 (Entity) 대상으로 쿼리를 날림.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
