package com.daroguzo.simplelms.member.service;

import com.daroguzo.simplelms.member.model.MemberDto;
import com.daroguzo.simplelms.member.model.ResetPasswordDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    /**
     * 회원 가입
     */
    boolean register(MemberDto memberDto);

    /**
     * uuid에 해당하는 계정 활성화
     */
    boolean emailAuth(String uuid);

    /**
     * 패스워드 초기화 요청
     */
    boolean sendResetPassword(ResetPasswordDto dto);
}
