package com.ozden.tweetr.tweet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tweet {

    private String ownerName;
    private String content;
    private LocalDateTime creationDate;
}
