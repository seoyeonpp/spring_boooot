package com.example.hello;

import com.example.hello.aop.TimeTraceAop;
import com.example.hello.repository.*;
import com.example.hello.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


// @Autowired 안쓰고 자바 코드로 직접 스프링 빈 등록하는 방법
@Configuration
public class SpringConfig {

    // spring data jpa 사용할때
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    스프링빈에 AOP 등록
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

//    이건 jdbc 사용할때
//    private final DataSource dataSource;

//    @PersistenceContext 원래 스펙에서는 이렇게 받아야하는데 스프링에서 아래와같이 DI 해줌
    // jpa 를 사용한다면 entitymanager을 넘겨줘야하기때문에~
//    private EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
