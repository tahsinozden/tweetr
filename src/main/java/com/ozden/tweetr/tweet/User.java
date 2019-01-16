package com.ozden.tweetr.tweet;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {

    private String userName;
    private List<Tweet> tweets;
    private List<User> followers;
    private List<User> followings;
}
