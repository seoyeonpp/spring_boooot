package com.example.hello.controller;

import com.example.hello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
//    controller에서 외부 요청을 받음
//    spring container가 생성될때 memberController 객체를 생성해서 spring container에 넣어둔다. 컨트롤러에서 스프링 빈이 관리된다.

    private final MemberService memberService;

//    controller랑 service를 연결해줄때 @Autowired 어노테이션 사용
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
