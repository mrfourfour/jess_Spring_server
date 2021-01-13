package com.example.twiterproject.feed.controller;


import com.example.twiterproject.feed.domain.Feed;
import com.example.twiterproject.feed.domain.FeedRepository;
import com.example.twiterproject.feed.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final FeedRepository feedRepository;

    @PostMapping("/{feedId}/comments")
    public void post(@PathVariable Long feedId, @RequestParam String feedComment){
        commentService.commentWrite(feedId,feedComment);
    }

    @PostMapping("/{feedId}/comments/{commentId}/likes")
    public void clickFeedLikes(@PathVariable Long feedId, @PathVariable Long commentId){
        commentService.clickFeedCommentLikes(feedId, commentId);
    }

}
