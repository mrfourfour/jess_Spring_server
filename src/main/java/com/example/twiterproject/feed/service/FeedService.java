package com.example.twiterproject.feed.service;

import com.example.twiterproject.feed.domain.Feed;
import com.example.twiterproject.feed.domain.FeedRepository;
import com.example.twiterproject.feed.domain.Writer;
import com.example.twiterproject.member.domain.Member;
import com.example.twiterproject.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public Feed writeFeed(String feedContent) {

        //인증 조회
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nickname = authentication.getName();
        Optional<Member> member = memberRepository.findByNickname(nickname);

        Writer writer = new Writer(member.get().getId(), nickname);
        Feed feed = Feed.createFeed(writer, feedContent);
        return feedRepository.save(feed);
    }

    // 친구 부분 구현후 해결책 찾는 걸로
    @Transactional
    public List<Feed> findAllFeed(Integer page, Integer size) {

        Page<Feed> feedPage = feedRepository.findAll(PageRequest.of(page, size));

        return feedPage.toList();
    }

    @Transactional
    public Optional<Feed> checkFeed(Long feedId) {

        Optional<Feed> feed = feedRepository.findById(feedId);
        return feed;
    }


    @Transactional
    public void delete(Long feedId) {

        //인증 조회
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nickname = authentication.getName();
        Optional<Member> member = memberRepository.findByNickname(nickname);

        Feed feed = feedRepository.findById(feedId).orElseThrow();

        if(feed.getWriter().getWriterId()==member.get().getId()){
            feed.delete();
            feedRepository.save(feed);
        }

    }

    // 좀더 찾아보기
    @Transactional
    public void clickFeedLikes(Long feedId) {

        //인증 조회
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nickname = authentication.getName();
        Optional<Member> member = memberRepository.findByNickname(nickname);

        Feed feed = feedRepository.findById(feedId).orElseThrow();
        feed.getLikesCount();

    }
}
