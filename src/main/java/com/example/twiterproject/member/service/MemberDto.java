package com.example.twiterproject.member.service;

import com.example.twiterproject.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class MemberDto {
    private String username;
    private String password;
    private String role;

    public MemberDto (String username, String password){
        this.username=username;
        this.password=password;
    }

    public Member toEntity(){

        return Member.createMember(username, password, role);
    }
}
