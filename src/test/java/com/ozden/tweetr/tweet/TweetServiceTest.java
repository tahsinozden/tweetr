package com.ozden.tweetr.tweet;

import com.ozden.tweetr.data.DataService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TweetServiceTest {

    @Mock
    private DataService dataService;

    @InjectMocks
    private TweetService tweetService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSaveUser() {
        tweetService.saveUser("newUser");
        verify(dataService).saveUser("newUser");
    }

    @Test
    public void shouldSaveTweetForExistingUser() {
        User user = new User("existingUser", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        when(dataService.getUserByName("existingUser")).thenReturn(Optional.of(user));
        tweetService.saveTweet("existingUser", "new tweet");
        verify(dataService, never()).saveUser(anyString());
        assertThat(user.getTweets()).isNotEmpty();
    }

    @Test
    public void shouldNotSaveTweetGreaterThanMaxSize() {
        User user = new User("user", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        String longTweet = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        tweetService.saveTweet("user", longTweet);
        verify(dataService, never()).saveUser(anyString());
        assertThat(user.getTweets()).isEmpty();
    }

    @Test
    public void shouldSaveTweetForNonExistingUser() {
        User user = new User("newUser", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        when(dataService.getUserByName("newUser")).thenReturn(Optional.empty());
        when(dataService.saveUser("newUser")).thenReturn(user);
        tweetService.saveTweet("newUser", "new tweet");
        verify(dataService).saveUser("newUser");
        assertThat(user.getTweets()).isNotEmpty();
    }

    @Test
    public void shouldGetTweetsByUserNameWhenUserExists() {
        User user = new User("user", Arrays.asList(new Tweet("user", "new tweet", LocalDateTime.now())), new ArrayList<>(), new ArrayList<>());
        when(dataService.getUserByName("user")).thenReturn(Optional.of(user));
        List<Tweet> actual = tweetService.getTweetsByUserName("user");
        assertThat(actual).isNotEmpty();
    }

    @Test
    public void shouldGetEmptyTweetsByUserNameWhenUserNotExists() {
        when(dataService.getUserByName("user")).thenReturn(Optional.empty());
        List<Tweet> actual = tweetService.getTweetsByUserName("user");
        assertThat(actual).isEmpty();
    }

    @Test
    public void shouldGetFollowingUserTweetsByUserName() {
        User srcUser = new User("srcUser", Arrays.asList(new Tweet("srcUser", "new1 tweet", LocalDateTime.now())), new ArrayList<>(), new ArrayList<>());
        User user1 = new User("user1", Arrays.asList(new Tweet("user1", "new2 tweet", LocalDateTime.now())), new ArrayList<>(), new ArrayList<>());
        User user2 = new User("user2", Arrays.asList(new Tweet("user2", "new3 tweet", LocalDateTime.now())), new ArrayList<>(), new ArrayList<>());
        srcUser.getFollowings().add(user1);
        srcUser.getFollowings().add(user2);

        when(dataService.getUserByName("srcUser")).thenReturn(Optional.of(srcUser));

        List<Tweet> actual = tweetService.getFollowingUserTweetsByUserName("srcUser");
        assertThat(actual).hasSize(2);
        assertThat(actual.get(0).getOwnerName()).isEqualTo("user1");
        assertThat(actual.get(1).getOwnerName()).isEqualTo("user2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFollowWhenOneNotExist() {
        tweetService.followUser("user1", "user2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotUnfollowWhenOneNotExist() {
        tweetService.unfollowUser("user1", "user2");
    }

    @Test
    public void shouldFollowUserWhenBothExist() {
        User user1 = new User("user1", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        when(dataService.getUserByName("user1")).thenReturn(Optional.of(user1));

        User user2 = new User("user2", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        when(dataService.getUserByName("user2")).thenReturn(Optional.of(user2));

        tweetService.followUser("user1", "user2");
        assertThat(user1.getFollowings()).hasSize(1);
        assertThat(user1.getFollowings().get(0)).isEqualTo(user2);
    }

    @Test
    public void shouldUnfollowUserWhenBothExist() {
        User user1 = new User("user1", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        when(dataService.getUserByName("user1")).thenReturn(Optional.of(user1));

        User user2 = new User("user2", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        when(dataService.getUserByName("user2")).thenReturn(Optional.of(user2));

        user1.getFollowings().add(user2);

        tweetService.unfollowUser("user1", "user2");
        assertThat(user1.getFollowings()).isEmpty();
    }
}