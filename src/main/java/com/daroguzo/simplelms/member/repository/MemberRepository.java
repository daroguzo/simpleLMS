package com.daroguzo.simplelms.member.repository;

import com.daroguzo.simplelms.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAuthKey(String emailAuthKey);

    Optional<Member> findByEmailAndUsername(String email, String username);

    Optional<Member> findByResetPasswordKey(String resetPasswordKey);
}