package com.daroguzo.simplelms.admin.controller;

import com.daroguzo.simplelms.admin.dto.MemberDto;
import com.daroguzo.simplelms.admin.model.MemberParam;
import com.daroguzo.simplelms.member.service.MemberService;
import com.daroguzo.simplelms.util.PageUtil;
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

        memberParam.init();

        List<MemberDto> members = memberService.list(memberParam);
        model.addAttribute("list", members);

        long totalCount = 0;
        if (members != null && members.size() > 0) {
            totalCount = members.get(0).getTotalCount();
        }
        String queryString = memberParam.getQueryString();

        PageUtil pageUtil = new PageUtil(totalCount, memberParam.getPageSize(), memberParam.getPageIndex(), queryString);
        model.addAttribute("pager", pageUtil.pager());
        model.addAttribute("totalCount", totalCount);

        return "/admin/member/list";
    }

}
