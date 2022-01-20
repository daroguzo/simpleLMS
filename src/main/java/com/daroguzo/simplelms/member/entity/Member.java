package com.daroguzo.simplelms.member.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue
    private long id;

    private String email;
    private String username;
    private String phone;
    private String password;
    private LocalDateTime regDt;

    private boolean isEmailAuthorized;
    private LocalDateTime emailAuthDt;
    private String emailAuthKey;

    private String resetPasswordKey;
    private LocalDateTime resetPasswordLimitDt;

    private boolean isAdmin;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;
}
