package com.ozden.tweetr.tweet;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {

    private String userName;
    private List<Tweet> tweets;
    private List<User> followers;
    private List<User> followings;

    public User(String userName) {
        this.userName = userName;
        this.tweets = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.followings = new ArrayList<>();
    }
}
