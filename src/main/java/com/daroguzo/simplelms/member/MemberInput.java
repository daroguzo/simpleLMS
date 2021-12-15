package com.daroguzo.simplelms.member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInput {

    private String userId;
    private String username;
    private String password;
    private String phone;
}
