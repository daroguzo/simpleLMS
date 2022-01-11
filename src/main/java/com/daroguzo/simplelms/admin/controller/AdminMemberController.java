package com.daroguzo.simplelms.admin.controller;

import com.daroguzo.simplelms.admin.dto.MemberDto;
import com.daroguzo.simplelms.admin.model.MemberParam;
import com.daroguzo.simplelms.member.entity.Member;
import com.daroguzo.simplelms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/member")
@Controller
public class AdminMemberController {

    private final MemberService memberService;

    @GetMapping("/list.do")
    public String list(Model model, MemberParam memberParam) {

        List<MemberDto> members = memberService.list(memberParam);
        model.addAttribute("list", members);

        return "/admin/member/list";
    }

}