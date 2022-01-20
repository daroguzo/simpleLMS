package com.daroguzo.simplelms.member.model;

import com.daroguzo.simplelms.member.entity.MemberStatus;
import lombok.Data;

@Data
public class MemberStatusInput {

    String email;
    MemberStatus memberStatus;
}
