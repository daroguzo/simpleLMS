package com.daroguzo.simplelms.member.controller;

import com.daroguzo.simplelms.member.model.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @GetMapping("/member/register")
    public String register() {
        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(MemberDto memberDto) {


        return "member/register";
    }
}
