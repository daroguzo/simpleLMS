package com.daroguzo.simplelms.member.model;

import com.daroguzo.simplelms.member.entity.MemberStatus;
import lombok.Data;

@Data
public class MemberAdminInput {

    String email;
    MemberStatus memberStatus;
    String password;
}
