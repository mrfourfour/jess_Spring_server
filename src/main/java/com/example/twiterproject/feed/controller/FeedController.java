package com.example.twiterproject.feed.controller;

import com.example.twiterproject.feed.domain.Feed;
import com.example.twiterproject.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feeds")
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;

    @GetMapping
    public List<Feed> findAllFeed  (
            @RequestParam Integer page,
            @RequestParam Integer size
    ){
        return feedService.findAllFeed(page, size);
    }

    @PostMapping
    public Feed writeFeed(@RequestBody FeedRequest body){

        return feedService.writeFeed(body.getContent());
    }

    @GetMapping("/{feedId}")
    public Feed checkFeed(@PathVariable Long feedId){

        return feedService.checkFeed(feedId);
    }

    @PostMapping("/{feedId}/likes")
    public void clickFeedLikes(@PathVariable Long feedId){
        feedService.clickFeedLikes(feedId);
    }


    @DeleteMapping("/{feedId}")
    public void DeleteFeed(@PathVariable Long feedId){
        feedService.delete(feedId);
    }

    @Value
    private static class FeedRequest {
        String content;
    }

}
