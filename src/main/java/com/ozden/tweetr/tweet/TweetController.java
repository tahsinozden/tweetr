package com.ozden.tweetr.tweet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    /**
     * saves a user tweet if user doesn't exist, it creates
     * @param userName
     * @param tweetContent
     */
    @PostMapping("/tweets/{user}/save")
    public void saveTweet(@PathVariable("user") String userName, @RequestParam("tweetContent") String tweetContent) {
        tweetService.saveTweet(userName, tweetContent);
    }

    /**
     * all tweets of the user
     * @param userName
     * @return returns all tweets of the user
     */
    @GetMapping("/tweets/{user}")
    public List<Tweet> getTweetsByUserName(@PathVariable("user") String userName) {
        return tweetService.getTweetsByUserName(userName);
    }

    /**
     * all tweets from user's following list
     * @param userName
     * @return returns tweets from user's following list
     */
    @GetMapping("/tweets/{user}/following")
    public List<Tweet> getFollowingUserTweetsByUserName(@PathVariable("user") String userName) {
        return tweetService.getFollowingUserTweetsByUserName(userName);
    }

    /**
     * follow user
     * @param srcUser current user
     * @param dstUser user to be followed
     */
    @PostMapping("/users/{srcUser}/follow/{dstUser}")
    public void followUser(@PathVariable("srcUser") String srcUser,
                           @PathVariable("dstUser") String dstUser) {
        tweetService.followUser(srcUser, dstUser);
    }

    /**
     * unfollow user
     * @param srcUser current user
     * @param dstUser user to be unfollowed
     */
    @PostMapping("/users/{srcUser}/unfollow/{dstUser}")
    public void unfollowUser(@PathVariable("srcUser") String srcUser,
                             @PathVariable("dstUser") String dstUser) {
        tweetService.unfollowUser(srcUser, dstUser);
    }
}
