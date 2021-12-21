package com.daroguzo.simplelms.member.service;

import com.daroguzo.simplelms.component.MailComponents;
import com.daroguzo.simplelms.member.entity.Member;
import com.daroguzo.simplelms.member.model.MemberDto;
import com.daroguzo.simplelms.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;

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

        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder()
                .email(memberDto.getEmail())
                .username(memberDto.getUsername())
                .password(memberDto.getPassword())
                .phone(memberDto.getPhone())
                .regDt(LocalDateTime.now())
                .isEmailAuthorized(false)
                .emailAuthKey(uuid)
                .build();
        memberRepository.save(member);

        sendAuthEmail(memberDto.getEmail(), uuid);

        return true;
    }

    /**
     * uuid에 해당하는 계정 활성화
     */
    @Transactional
    @Override
    public boolean emailAuth(String uuid) {
        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);
        if (optionalMember.isEmpty()) {
            return false;
        }

        Member member = optionalMember.get();
        member.setEmailAuthorized(true);
        member.setEmailAuthDt(LocalDateTime.now());
        memberRepository.save(member);

        return true;
    }

    private void sendAuthEmail(String email, String uuid) {
        String subject = "LMS 시스템에 오신 것을 환영합니다.";
        String text = "<h2>LMS 시스템 가입 안내<h2>" +
                "<p>아래 링크를 클릭하고 가입 절차를 완료하세요.</p>" +
                "<div><a href='http://localhost:8080/member/email-auth?uuid=" + uuid + "'>가입 완료</a></div>";
        mailComponents.sendMail(email, subject, text);
    }
}
