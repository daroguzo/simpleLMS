package com.daroguzo.simplelms.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@RequiredArgsConstructor
@Controller
public class AdminMainController {

    @GetMapping("/main")
    public String main() {

        return "admin/main";
    }
}
