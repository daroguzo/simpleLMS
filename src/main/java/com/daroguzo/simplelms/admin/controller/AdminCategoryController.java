package com.daroguzo.simplelms.admin.controller;

import com.daroguzo.simplelms.admin.dto.MemberDto;
import com.daroguzo.simplelms.admin.model.MemberParam;
import com.daroguzo.simplelms.member.model.MemberAdminInput;
import com.daroguzo.simplelms.member.service.MemberService;
import com.daroguzo.simplelms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/category")
@Controller
public class AdminCategoryController {

    @GetMapping("/list.do")
    public String list(Model model, MemberParam memberParam) {

        return "/admin/category/list";
    }
}
