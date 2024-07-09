package com.example.hello.service;

import com.example.hello.domain.Member;
import com.example.hello.repository.MemberRepository;
import com.example.hello.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
//    service에서 비즈니스 로직을 처리
    private final MemberRepository memberRepository;

//    dependency injection == DI 의존성 주입
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        // result.orElseGet() : 값이 있으면 꺼내오고 없으면 뒤에꺼 실행
//        result.ifPresent(m -> {
//            // Optional로 감싸서 ifPresent를 사용할 수 있음.
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        // 위 로직을 더 깔끔하게 작성
        validateDuplicateMember(member);
        memberRepository.save(member);

       return member.getId();
    }

    /**
     * 중복 회원 검증 메서드
     */
    private void validateDuplicateMember(Member member) {
        // 같은 이름이 있는 중복 회원X
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 한명 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
