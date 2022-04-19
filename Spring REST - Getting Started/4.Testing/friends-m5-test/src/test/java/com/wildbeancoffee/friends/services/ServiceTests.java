package com.wildbeancoffee.friends.services;


import com.wildbeancoffee.friends.model.Friend;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTests {

  @Autowired
  FriendService friendService;

  @Test
  public void testCreateReadDelete() {
    Friend friend = new Friend("Gordon", "Moore");

    friendService.save(friend);

    Iterable<Friend> friends = friendService.findAll();
    Assertions.assertThat(friends).extracting(Friend::getFirstName).containsOnly("Gordon");

    friendService.deleteAll();
    Assertions.assertThat(friendService.findAll()).isEmpty();
  }
}
