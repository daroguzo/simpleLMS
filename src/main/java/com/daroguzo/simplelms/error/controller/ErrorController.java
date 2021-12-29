package com.daroguzo.simplelms.error.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/error")
@Controller
public class ErrorController {

    @GetMapping("/denied")
    public String denied() {
        return "error/denied";
    }
}
