package com.example.twiterproject.feed.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class Writer {
    @Column(name = "writer_id")
    private Long writerId;
    @Column(name = "writer_nickname")
    private String writerNickname;

    public Writer(Long writerId,String writerNickname){
        this.writerId=writerId;
        this.writerNickname=writerNickname;
    }
}
