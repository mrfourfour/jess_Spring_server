package com.example.twiterproject.member.domain;

//
//import com.example.twiterproject.friendShip.domain.Friendship;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @JsonIgnore
    private String password;
    private String role;

//    @OneToMany
//    private List<Friendship> friendList;
//
//    @OneToMany
//    private List<Friendship> friendRequestList;



    private Member(String username, String password, String role) {
        this.username=username;
        this.password=password;
        this.role=role;
    }


    public static Member createMember(String username, String password, String role){
        return new Member(username,password, role);
    }

}
