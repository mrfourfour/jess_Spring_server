package com.example.twiterproject.feed.domain;
//
//import com.example.twiterproject.likes.domain.Likes;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PACKAGE)
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Writer writer;
    private String content;
    private Long likesCount;

    private LocalDateTime reportingDate;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.PERSIST)
    private List<FeedComment> feedComments;

//    @OneToMany(mappedBy = "feed", cascade = CascadeType.PERSIST)
//    private List<Likes> feedLikes;

    private Boolean deletedFlag;

    private Feed(Writer writer, String content) {
        this.writer = writer;
        this.content = content;
        this.likesCount=0l;
        this.deletedFlag = false;
    }

    public static Feed createFeed(Writer writer, String content) {
        return new Feed(writer, content);
    }

    @PrePersist
    private void reportingTimePersist() {
        setReportingDate(LocalDateTime.now());
    }

    public void delete() {
        this.deletedFlag = true;
    }

    public void enrollComment(FeedComment comment) {
        comment.setFeed(this);
        feedComments.add(comment);
    }

    public void clickLikes() {
        likesCount+=1;
    }


}
