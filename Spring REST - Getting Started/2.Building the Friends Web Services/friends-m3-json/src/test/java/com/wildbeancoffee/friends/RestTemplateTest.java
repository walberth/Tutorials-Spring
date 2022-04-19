package com.wildbeancoffee.friends;

import com.wildbeancoffee.friends.controllers.FriendController;
import com.wildbeancoffee.friends.model.Friend;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateTest{

  TestRestTemplate restTemplate = new TestRestTemplate();

  @Test
  public void testTemplate() {

        String friendUrl
        = "http://localhost:8080/friend/1";
    Friend result
        = restTemplate.getForObject(friendUrl, Friend.class);

    Assert.assertEquals("Doe", result.getLastName());

  }


}
