package com.daroguzo.simplelms.admin.controller;

import com.daroguzo.simplelms.admin.dto.CategoryDto;
import com.daroguzo.simplelms.admin.dto.MemberDto;
import com.daroguzo.simplelms.admin.model.CategoryInput;
import com.daroguzo.simplelms.admin.model.MemberParam;
import com.daroguzo.simplelms.admin.service.CategoryService;
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

    private final CategoryService categoryService;

    @GetMapping("/list.do")
    public String list(Model model) {

        List<CategoryDto> list = categoryService.list();
        model.addAttribute("list", list);

        return "/admin/category/list";
    }

    @PostMapping("/add.do")
    public String add(CategoryInput input) {

        boolean result = categoryService.add(input.getCategoryName());

        return "redirect:/admin/category/list.do";
    }

    @PostMapping("/delete.do")
    public String delete(CategoryInput input) {
        boolean result = categoryService.delete(input.getId());

        return "redirect:/admin/category/list.do";
    }

    @PostMapping("/update.do")
    public String update(CategoryInput input) {
        boolean result = categoryService.update(input);

        return "redirect:/admin/category/list.do";
    }
}
