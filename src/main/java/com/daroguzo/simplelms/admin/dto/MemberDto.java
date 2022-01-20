package com.daroguzo.simplelms.admin.dto;

import com.daroguzo.simplelms.member.entity.Member;
import com.daroguzo.simplelms.member.entity.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberDto {
    long id;
    String email;
    String username;
    String phone;
    String password;
    LocalDateTime regDt;

    boolean isEmailAuthorized;
    LocalDateTime emailAuthDt;
    String emailAuthKey;

    String resetPasswordKey;
    LocalDateTime resetPasswordLimitDt;

    boolean isAdmin;
    MemberStatus memberStatus;

    // 전체 개수
    long totalCount;
    // sequence
    long seq;

    public static MemberDto of(Member member) {

        return MemberDto.builder()
                .email(member.getEmail())
                .username(member.getUsername())
                .phone(member.getPhone())
                //.password(member.getPassword())
                .regDt(member.getRegDt())
                .isEmailAuthorized(member.isEmailAuthorized())
                .emailAuthDt(member.getEmailAuthDt())
                .emailAuthKey(member.getEmailAuthKey())
                .resetPasswordKey(member.getResetPasswordKey())
                .resetPasswordLimitDt(member.getResetPasswordLimitDt())
                .isAdmin(member.isAdmin())
                .memberStatus(member.getMemberStatus())
                .build();
    }
}
