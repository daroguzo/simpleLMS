package com.daroguzo.simplelms.member.controller;

import com.daroguzo.simplelms.member.model.MemberDto;
import com.daroguzo.simplelms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/register")
    public String register() {
        return "member/register";
    }

    @PostMapping("/register")
    public String registerSubmit(Model model, HttpServletRequest request, MemberDto memberDto) {

        boolean result = memberService.register(memberDto);
        model.addAttribute("result", result);

        return "member/register_complete";
    }

    @GetMapping("/email-auth")
    public String emailAuthorize(@RequestParam("uuid") String uuid, Model model) {

        boolean result = memberService.emailAuth(uuid);
        System.out.println(result);
        model.addAttribute("result", result);

        return "member/email_auth";
    }

    @GetMapping("/info")
    public String memberInfo() {
        return "member/info";
    }

    @GetMapping
    public String findPassword() {
        return "member/find_password";
    }
}
