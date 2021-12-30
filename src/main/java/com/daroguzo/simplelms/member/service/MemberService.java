package com.daroguzo.simplelms.member.service;

import com.daroguzo.simplelms.admin.dto.MemberDto;
import com.daroguzo.simplelms.member.entity.Member;
import com.daroguzo.simplelms.member.model.MemberInput;
import com.daroguzo.simplelms.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {

    /**
     * 회원 가입
     */
    boolean register(MemberInput memberInput);

    /**
     * uuid에 해당하는 계정 활성화
     */
    boolean emailAuth(String uuid);

    /**
     * 패스워드 초기화 요청
     */
    boolean sendResetPassword(ResetPasswordInput dto);

    /**
     * 입력 받은 uuid 확인을 통해 비밀번호 초기화
     */
    boolean resetPassword(String uuid, String password);

    /**
     * 입력 받은 uuid값이 유효한지 확인
     */
    boolean checkResetPassword(String uuid);

    /**
     * 회원 목록 반환(admin)
     */
    List<MemberDto> list();
}
