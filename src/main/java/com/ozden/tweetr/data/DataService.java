package com.ozden.tweetr.data;

import com.ozden.tweetr.tweet.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DataService {

    private static final Map<String, User> USER_BY_NAME = new ConcurrentHashMap<>();

    public User saveUser(String userName) {
        User user = new User(userName);
        USER_BY_NAME.put(userName, user);
        return user;
    }

    public Optional<User> getUserByName(String userName) {
        User user = USER_BY_NAME.get(userName);
        return Optional.ofNullable(user);
    }
}
