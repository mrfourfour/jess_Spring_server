package com.example.twiterproject.feed.service;

import com.example.twiterproject.feed.domain.Feed;
import com.example.twiterproject.feed.domain.FeedComment;
import com.example.twiterproject.feed.domain.FeedRepository;
import com.example.twiterproject.feed.domain.Writer;
import com.example.twiterproject.member.domain.Member;
import com.example.twiterproject.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final FeedRepository feedRepository;

    @Transactional
    public void commentWrite(Long feedId,String comment) {

        //인증 조회
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nickname = authentication.getName();
        Optional<Member> member = memberRepository.findByNickname(nickname);

        Feed feed=feedRepository.findById(feedId).orElseThrow();
        Writer writer = new Writer(member.get().getId(), nickname);
        FeedComment feedComment = FeedComment.createFeedComment(writer,feed,comment);

        feed.enrollComment(feedComment);
    }

    // 로직 찾아보기
    public void clickFeedCommentLikes(Long id, Long feedId) {

    }
}
