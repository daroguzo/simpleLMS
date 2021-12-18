package com.daroguzo.simplelms.member.controller;

import com.daroguzo.simplelms.member.entity.Member;
import com.daroguzo.simplelms.member.model.MemberDto;
import com.daroguzo.simplelms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/register")
    public String register() {
        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(HttpServletRequest request, MemberDto memberDto) {

        Member member = new Member();
        member.setEmail(memberDto.getEmail());
        member.setUsername(memberDto.getUsername());
        member.setPassword(memberDto.getPassword());
        member.setPhone(memberDto.getPhone());
        member.setRegDt(LocalDateTime.now());

        memberService.join(member);

        return "member/register_complete";
    }
}
