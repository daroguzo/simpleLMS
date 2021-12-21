package com.daroguzo.simplelms.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private long id;

    private String email;
    private String username;
    private String phone;
    private String password;
    private LocalDateTime regDt;

    private boolean isEmailAuthorized;
    private String emailAuthKey;
}
