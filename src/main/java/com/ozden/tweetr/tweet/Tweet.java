package com.ozden.tweetr.tweet;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Tweet {

    private String ownerName;
    private String content;
    private LocalDateTime creationDate;
}
