package com.example.twiterproject.member.service;

import com.example.twiterproject.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String nickname;
    private String password;


    public Member toEntity(){

        return Member.createMember(nickname, password);
    }
}
