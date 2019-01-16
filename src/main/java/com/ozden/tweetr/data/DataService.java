package com.ozden.tweetr.data;

import com.ozden.tweetr.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DataService {

    private static final Map<String, User> ALL_USERS_BY_NAME = new ConcurrentHashMap<>();

    public User saveUser(String userName) {
        User user = new User(userName, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        ALL_USERS_BY_NAME.put(userName, user);
        return user;
    }

    public Optional<User> getUserByName(String name) {
        User user = ALL_USERS_BY_NAME.get(name);
        return Optional.ofNullable(user);
    }
}
