package com.daroguzo.simplelms.member.controller;

import com.daroguzo.simplelms.member.model.MemberInput;
import com.daroguzo.simplelms.member.model.ResetPasswordInput;
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
    public String registerSubmit(Model model, HttpServletRequest request, MemberInput memberInput) {

        boolean result = memberService.register(memberInput);
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

    @GetMapping("/find/password")
    public String getFindPassword() {
        return "member/find_password";
    }

    @PostMapping("/find/password")
    public String postFindPassword(Model model, ResetPasswordInput dto) {
        boolean result = false;

        try {
            result = memberService.sendResetPassword(dto);
        } catch (Exception e) {

        }
        model.addAttribute("result", result);

        return "member/find_password_result";
    }

    @GetMapping("/reset/password")
    public String getResetPassword(@RequestParam("uuid") String uuid,
                                   Model model) {
        boolean result = memberService.checkResetPassword(uuid);
        model.addAttribute("result", result);

        return "/member/reset_password";
    }

    @PostMapping("/reset/password")
    public String PostResetPassword(Model model, ResetPasswordInput dto) {
        boolean result = false;
        try {
            result = memberService.resetPassword(dto.getUuid(), dto.getPassword());
        } catch (Exception e) {
        }

        model.addAttribute("result", result);
        model.addAttribute("dto", dto);

        return "/member/reset_password_result";
    }

}
