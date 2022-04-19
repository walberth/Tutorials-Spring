package com.wildbeancoffee.friends;

import com.wildbeancoffee.friends.model.Friend;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class SystemTests {

  @Test
  public void testCreateReadDelete() {
    RestTemplate restTemplate = new RestTemplate();

    String url = "http://localhost:8080/friend";

    Friend friend = new Friend("Gordon", "Moore");
    ResponseEntity<Friend> entity = restTemplate.postForEntity(url, friend, Friend.class);

    Friend[] friends = restTemplate.getForObject(url, Friend[].class);
    Assertions.assertThat(friends).extracting(Friend::getFirstName).containsOnly("Gordon");

    restTemplate.delete(url + "/" + entity.getBody().getId());
    Assertions.assertThat(restTemplate.getForObject(url, Friend[].class)).isEmpty();
  }

  @Test
  public void testErrorHandlingReturnsBadRequest() {

    RestTemplate restTemplate = new RestTemplate();

    String url = "http://localhost:8080/wrong";

    try {
      restTemplate.getForEntity(url, String.class);
    } catch (HttpClientErrorException e) {
      Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }
  }

}
