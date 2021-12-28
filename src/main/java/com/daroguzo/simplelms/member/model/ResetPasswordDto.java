package com.daroguzo.simplelms.member.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ResetPasswordDto {
    private String email;
    private String username;
    private String password;
    private String uuid;
}
