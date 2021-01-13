package com.example.twiterproject.feed.domain;
//
//import com.example.twiterproject.likes.domain.Likes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PACKAGE)
public class FeedComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Writer writer; // varchar(50) 로그인 해야한다고 했으니까 NOT

    @ManyToOne
    @JoinColumn(name = "feed_id")
    @JsonIgnore
    private Feed feed;
    private String content; //mediumtext(데이터타입임)
    private Long likesCount;
    private LocalDateTime reportingDate; // datetime

//    @OneToMany(mappedBy = "feed_comment", cascade = CascadeType.PERSIST)
//    private List<Likes> feedCommentLikes;

    private FeedComment(Writer writer, Feed feed, String content){
        this.writer=writer;
        this.content=content;
        this.feed=feed;
        this.likesCount=0l;
    }
    public static FeedComment createFeedComment(Writer writer,Feed feed,String content){
        return new FeedComment(writer,feed,content);
    }

    @PrePersist
    private void reportingDatePersist(){
        setReportingDate(LocalDateTime.now());
    }
}
