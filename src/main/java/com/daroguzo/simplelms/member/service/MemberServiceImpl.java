package com.daroguzo.simplelms.member.service;

import com.daroguzo.simplelms.member.entity.Member;
import com.daroguzo.simplelms.member.model.MemberDto;
import com.daroguzo.simplelms.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    @Override
    public boolean register(MemberDto memberDto) {

        Optional<Member> optionalMember = memberRepository.findByEmail(memberDto.getEmail());
        if (optionalMember.isPresent()) {
            // 이미 같은 email 존재
            return false;
        }

        Member member = new Member();
        member.setEmail(memberDto.getEmail());
        member.setUsername(memberDto.getUsername());
        member.setPassword(memberDto.getPassword());
        member.setPhone(memberDto.getPhone());
        member.setRegDt(LocalDateTime.now());

        memberRepository.save(member);
        return true;
    }
}
