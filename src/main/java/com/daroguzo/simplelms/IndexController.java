package com.daroguzo.simplelms;

import com.daroguzo.simplelms.component.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final MailComponents mailComponents;

    @GetMapping("/")
    public String index() {

        mailComponents.sendMailTest();

        return "index";
    }
}
