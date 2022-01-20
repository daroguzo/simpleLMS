package com.daroguzo.simplelms.member.entity;

import java.util.stream.Stream;

public enum MemberStatus {
    // 이용중인 사용자
    ING,
    // 정지된 사용자
    STOP,
    // 가입 요청중인 사용자
    REQ,
    // 오류
    UNKNOWN;

    public static MemberStatus findOrDefault(String status) {
        return Stream.of(values())
                .filter(v -> v.name().equalsIgnoreCase(status))
                .findAny()
                .orElse(UNKNOWN);
    }

    public boolean isIng() {
        return this == ING;
    }

    public boolean isStop() {
        return this == STOP;
    }

    public boolean isReq() {
        return this == REQ;
    }
}
