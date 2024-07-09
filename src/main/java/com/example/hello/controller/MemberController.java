package com.example.hello.controller;

import com.example.hello.domain.Member;
import com.example.hello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
//    controller에서 외부 요청을 받음
//    spring container가 생성될때 memberController 객체를 생성해서 spring container에 넣어둔다. 컨트롤러에서 스프링 빈이 관리된다.

//    controller랑 service를 연결해줄때 @Autowired 어노테이션 사용
//    DI 방법 1. 생성자 주입 방법 -> 요즘 제일 많이씀!!!! 의존관계가 실행중에 동적으로 변경되는 경우가 거의 없기때문에 생성자 주입을 권장함.
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    DI 방법 2. 필드 주입 -> 별로 안좋음.
// @Autowired private MemberService memberService;

//    DI 방법 3. setter injection 방식 -> public으로 열려있어야함. 중간에 잘못바꾸면 문제가 생길 수 있음.
//    private MemberService memberService;
//
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }


    /**
     * 회원가입 폼 보여주기
     */
    @GetMapping("/members/new")
    public String createForm() {
        return "/members/createMemberForm";
    }

    /**
     * 회원가입 처리
     */
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    /**
     * 회원목록 조회
     */
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "/members/memberList";
    }

}
