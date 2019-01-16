package com.ozden.tweetr;

import com.ozden.tweetr.tweet.Tweet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {

    private String userName;
    private List<Tweet> tweets;
    private List<User> followers;
    private List<User> followings;
}
