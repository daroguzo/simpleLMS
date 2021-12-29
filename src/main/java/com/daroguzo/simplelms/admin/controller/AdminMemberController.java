package com.daroguzo.simplelms.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/admin/member")
@Controller
public class AdminMemberController {

    @GetMapping("/list")
    public String list() {


        return "/admin/member/list";
    }
}
