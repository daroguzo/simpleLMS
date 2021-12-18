package com.daroguzo.simplelms.member.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDto {

    private String userId;
    private String username;
    private String password;
    private String phone;
}
