package com.daroguzo.simplelms.member.service;

import com.daroguzo.simplelms.admin.dto.MemberDto;
import com.daroguzo.simplelms.admin.mapper.MemberMapper;
import com.daroguzo.simplelms.admin.model.MemberParam;
import com.daroguzo.simplelms.component.MailComponents;
import com.daroguzo.simplelms.member.entity.Member;
import com.daroguzo.simplelms.member.entity.MemberStatus;
import com.daroguzo.simplelms.member.exception.MemberNotEmailAuthException;
import com.daroguzo.simplelms.member.exception.StopMemberException;
import com.daroguzo.simplelms.member.model.MemberInput;
import com.daroguzo.simplelms.member.model.ResetPasswordInput;
import com.daroguzo.simplelms.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;
    private final MemberMapper memberMapper;

    /**
     * 회원 가입
     */
    @Transactional
    @Override
    public boolean register(MemberInput memberInput) {

        Optional<Member> optionalMember = memberRepository.findByEmail(memberInput.getEmail());
        if (optionalMember.isPresent()) {
            // 이미 같은 email 존재
            return false;
        }

        String encodedPassword = BCrypt.hashpw(memberInput.getPassword(), BCrypt.gensalt());
        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder()
                .email(memberInput.getEmail())
                .username(memberInput.getUsername())
                .password(encodedPassword)
                .phone(memberInput.getPhone())
                .regDt(LocalDateTime.now())
                .isEmailAuthorized(false)
                .emailAuthKey(uuid)
                .memberStatus(MemberStatus.REQ)
                .build();
        memberRepository.save(member);

        sendAuthEmail(memberInput.getEmail(), uuid);

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

        if (member.isEmailAuthorized()) {
            return false;
        }

        member.setMemberStatus(MemberStatus.ING);
        member.setEmailAuthorized(true);
        member.setEmailAuthDt(LocalDateTime.now());
        memberRepository.save(member);

        return true;
    }

    @Transactional
    @Override
    public boolean sendResetPassword(ResetPasswordInput dto) {

        Optional<Member> byEmail = memberRepository.findByEmailAndUsername(
                dto.getEmail(),
                dto.getUsername());
        Member member = byEmail.orElseThrow(() -> new UsernameNotFoundException("헤당되는 사용자가 없습니다."));

        String uuid = UUID.randomUUID().toString();
        member.setResetPasswordKey(uuid);
        member.setResetPasswordLimitDt(LocalDateTime.now().plusHours(1));
        memberRepository.save(member);

        sendPasswordResetLink(dto.getEmail(), uuid);
        return true;
    }

    @Transactional
    @Override
    public boolean resetPassword(String uuid, String password) {

        Optional<Member> byResetPasswordKey = memberRepository.findByResetPasswordKey(uuid);
        Member member = byResetPasswordKey.orElseThrow(() -> new UsernameNotFoundException("uuid가 일치하지 않습니다."));

        // 초기화 메일 유효성 체크
        if (member.getResetPasswordLimitDt() == null) {
            throw new RuntimeException("유효한 날짜가 아닙니다.");
        }

        if (member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("유효한 날짜가 아닙니다.");
        }

        String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        member.setPassword(encodedPassword);
        member.setResetPasswordKey("");
        member.setResetPasswordLimitDt(null);
        memberRepository.save(member);

        return true;
    }

    @Override
    public boolean checkResetPassword(String uuid) {
        Optional<Member> byResetPasswordKey = memberRepository.findByResetPasswordKey(uuid);
        if (byResetPasswordKey.isEmpty()) {
            return false;
        }
        Member member = byResetPasswordKey.get();

        // 초기화 메일 유효성 체크
        if (member.getResetPasswordLimitDt() == null) {
            return false;
        }

        if (member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("유효한 날짜가 아닙니다.");
        }

        return true;
    }

    @Override
    public List<MemberDto> list(MemberParam memberParam) {

        long totalCount = memberMapper.selectListCount(memberParam);
        List<MemberDto> list = memberMapper.selectList(memberParam);

        // 총 개수 추가
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for(MemberDto m : list) {
                m.setTotalCount(totalCount);
                m.setSeq(totalCount - memberParam.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public MemberDto detail(String email) {
        Optional<Member> byEmail = memberRepository.findByEmail(email);
        Member member = byEmail.orElseThrow(() -> new UsernameNotFoundException("해당하는 사용자가 없습니다."));

        return MemberDto.of(member);
    }

    @Transactional
    @Override
    public boolean updateStatus(String email, MemberStatus memberStatus) {

        Member member = memberRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("회원 정보가 존재하지 않습니다."));

        member.setMemberStatus(memberStatus);
        memberRepository.save(member);

        return true;
    }

    @Transactional
    @Override
    public boolean updatePassword(String email, String password) {
        Member member = memberRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("회원 정보가 존재하지 않습니다."));

        String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        member.setPassword(encryptedPassword);
        memberRepository.save(member);
        return false;
    }

    private void sendAuthEmail(String email, String uuid) {
        String subject = "LMS 시스템에 오신 것을 환영합니다.";
        String text = "<h2>LMS 시스템 가입 안내<h2>" +
                "<p>아래 링크를 클릭하고 가입 절차를 완료하세요.</p>" +
                "<div><a href='http://localhost:8080/member/email-auth?uuid=" + uuid + "'>가입 완료</a></div>";
        mailComponents.sendMail(email, subject, text);
    }

    private void sendPasswordResetLink(String email, String uuid) {
        String subject = "LMS 시스템 비밀번호 초기화 링크 안내";
        String text = "<h2>LMS 시스템 비밀번호 초기화 링크 안내<h2>" +
                "<p>아래 링크를 클릭하고 비밀번호를 재설정하세요.</p>" +
                "<div><a href='http://localhost:8080/member/reset/password?uuid=" + uuid + "'>비밀번호 초기화 링크</a></div>";
        mailComponents.sendMail(email, subject, text);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("회원 정보가 존재하지 않습니다."));

        if (member.getMemberStatus().isReq()) {
            throw new MemberNotEmailAuthException("이메일 활성화가 되지 않았습니다.");
        }

        if (member.getMemberStatus().isStop()) {
            throw new StopMemberException("정지된 회원입니다.");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (member.isAdmin()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }


        return new User(member.getEmail(), member.getPassword(), grantedAuthorities);
    }
}
