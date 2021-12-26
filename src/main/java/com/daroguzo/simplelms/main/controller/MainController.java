package com.daroguzo.simplelms.main.controller;

import com.daroguzo.simplelms.component.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MailComponents mailComponents;

    @GetMapping("/")
    public String index() {

        return "index";
    }
}
