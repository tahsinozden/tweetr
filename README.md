# tweetr
a Twitter-like API coding task.

Testing can be performed using `Postman` the collection `tweetr.postman_collection.json`. Simply import it to `Postman` and start testing.

## API Documentation

**Save a user tweet (create user if not exists)**
----


* **URL**

  /tweets/:user/save

* **Method:**

  `POST`
  
*  **URL Params**


* **Data Params**

    **Required:**
    `tweetContent=[String]` max 140 character

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/tweets/user1/save",
      dataType: "json",
      type : "POST",
      success : function(r) {
        console.log(r);
      }
    });
    ```
**Get user's tweets**
----


* **URL**

  /api/tweets/:user

* **Method:**

  `GET`
  
*  **URL Params**

* **Data Params**

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/api/tweets/user1",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```

**Get user's following users' tweets**
----


* **URL**

  /api/tweets/:user/following

* **Method:**

  `GET`
  
*  **URL Params**

* **Data Params**

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/api/tweets/user1/following",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```
**Make user1 follow user2**
----


* **URL**

  /api/users/:user1/follow/:user2

* **Method:**

  `POST`
  
*  **URL Params**

* **Data Params**

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/api/users/user1/follow/user2",
      dataType: "json",
      type : "POST",
      success : function(r) {
        console.log(r);
      }
    });
    ```
**Make user1 unfollow user2**
----


* **URL**

  /api/users/:user1/unfollow/:user2

* **Method:**

  `POST`
  
*  **URL Params**

* **Data Params**

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/api/users/user1/unfollow/user2",
      dataType: "json",
      type : "POST",
      success : function(r) {
        console.log(r);
      }
    });
    ```
    
## Code Challenge

## Description

Build a simple social networking application, similar to Twitter, and
expose it through a web API. The application should support the scenarios
below.

## Scenarios

### Posting

A user should be able to post a 140 character message.

### Wall

A user should be able to see a list of the messages they've posted, in reverse
chronological order.

### Following

A user should be able to follow another user. Following doesn't have to be
reciprocal: Alice can follow Bob without Bob having to follow Alice.

### Timeline

A user should be able to see a list of the messages posted by all the people
they follow, in reverse chronological order.

## Details

- use JAVA
- provide some documentation for the API, so that we know how to use it!
- don't care about registering users: a user is created as soon as they post
  their first message
- don't care about user authentication
- don't care about frontend, only backend
- don't care about storage: storing everything in memory is fine
