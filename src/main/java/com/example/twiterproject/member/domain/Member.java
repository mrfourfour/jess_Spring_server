package com.example.twiterproject.member.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    @JsonIgnore
    private String password;



    private Member(String nickname, String password) {
        this.nickname=nickname;
        this.password=password;
    }


    public static Member createMember(String nickname, String password){
        return new Member(nickname,password);
    }

}
