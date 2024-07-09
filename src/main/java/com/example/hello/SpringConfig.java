package com.example.hello;

import com.example.hello.repository.JdbcMemberRepository;
import com.example.hello.repository.MemberRepository;
import com.example.hello.repository.MemoryMemberRepository;
import com.example.hello.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


// @Autowired 안쓰고 자바 코드로 직접 스프링 빈 등록하는 방법
@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
