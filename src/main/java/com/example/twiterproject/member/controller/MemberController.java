package com.example.twiterproject.member.controller;

import com.example.twiterproject.member.domain.Member;
import com.example.twiterproject.member.domain.MemberRepository;
import com.example.twiterproject.member.service.MemberDto;
import com.example.twiterproject.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/signup")
    public Member signUp(@RequestBody SignUpRequest body){
        MemberDto memberDto = new MemberDto(body.nickname, body.password);
        return memberService.signup(memberDto);

    }

    @PostMapping("/login")
    public Optional<Member> login(@RequestBody SignUpRequest body){
        memberService.loadUserByUsername(body.getNickname());
        return memberRepository.findByNickname(body.getNickname());
    }
    @Value
    public static class SignUpRequest {
        String nickname;
        String password;
    }
}
