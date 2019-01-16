package com.ozden.tweetr.tweet;

import com.ozden.tweetr.User;
import com.ozden.tweetr.data.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TweetService {

    private static final int MAX_TWEET_LENGTH = 140;

    @Autowired
    private DataService dataService;

    public User saveUser(String userName) {
        return dataService.saveUser(userName);
    }

    public void saveTweet(String userName, String content) {
        if (content == null || content.length() > MAX_TWEET_LENGTH) {
            return;
        }
        Optional<User> userOptional = dataService.getUserByName(userName);
        User user = userOptional.orElseGet(() -> dataService.saveUser(userName));
        user.getTweets().add(new Tweet(user.getUserName(), content, LocalDateTime.now()));
    }

    public List<Tweet> getTweetsByUserName(String userName) {
        Optional<User> userOptional = dataService.getUserByName(userName);
        if (!userOptional.isPresent()) {
            return Collections.emptyList();
        }
        User user = userOptional.get();
        List<Tweet> tweets = user.getTweets();
        tweets.sort((t1, t2) -> t2.getCreationDate().compareTo(t1.getCreationDate()));
//        Collections.reverse(user.getTweets());
        return tweets;
    }

    public List<Tweet> getFollowingUserTweetsByUserName(String userName) {
        Optional<User> userOptional = dataService.getUserByName(userName);
        if (!userOptional.isPresent()) {
            return Collections.emptyList();
        }
        User user = userOptional.get();

        List<Tweet> tweets = user.getFollowings().stream()
                .flatMap(u -> u.getTweets().stream())
                .sorted((t1, t2) -> t2.getCreationDate().compareTo(t1.getCreationDate()))
                .collect(Collectors.toList());
        //        Collections.sort(tweets, );
        return tweets;
    }

    public void followUser(String followerUserName, String followedUserName) {
        User srcUser = dataService.getUserByName(followerUserName)
                .orElseThrow(() -> new IllegalArgumentException(followerUserName + " doesn't exist!"));
        User dstUser = dataService.getUserByName(followedUserName)
                .orElseThrow(() -> new IllegalArgumentException(followedUserName + " doesn't exist!"));
        List<User> followings = srcUser.getFollowings();
        if (!followings.contains(dstUser)) {
            srcUser.getFollowings().add(dstUser);
        }
    }

    public void unfollowUser(String followerUserName, String followedUserName) {
        User srcUser = dataService.getUserByName(followerUserName)
                .orElseThrow(() -> new IllegalArgumentException(followerUserName + " doesn't exist!"));
        User dstUser = dataService.getUserByName(followedUserName)
                .orElseThrow(() -> new IllegalArgumentException(followedUserName + " doesn't exist!"));
        srcUser.getFollowings().remove(dstUser);
    }

}
