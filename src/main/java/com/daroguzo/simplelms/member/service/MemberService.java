package com.daroguzo.simplelms.member.service;

import com.daroguzo.simplelms.member.entity.Member;
import com.daroguzo.simplelms.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public Long join(Member member) {

        memberRepository.save(member);
        return member.getId();
    }
}
