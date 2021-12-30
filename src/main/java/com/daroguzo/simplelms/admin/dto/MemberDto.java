package com.daroguzo.simplelms.admin.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {
    long id;
    String email;
    String username;
    String phone;
    String password;
    LocalDateTime regDt;

    boolean isEmailAuthorized;
    LocalDateTime mailAuthDt;
    String emailAuthKey;

    String resetPassword_key;
    LocalDateTime resetPasswordLimitDt;

    boolean isAdmin;
}
