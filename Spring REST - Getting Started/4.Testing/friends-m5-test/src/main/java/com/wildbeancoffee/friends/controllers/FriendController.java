package com.wildbeancoffee.friends.controllers;

import com.wildbeancoffee.friends.model.Friend;
import com.wildbeancoffee.friends.services.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.ValidationException;
import java.util.Optional;

@RestController
public class FriendController {

  @Autowired
  FriendService friendService;

  @PostMapping("/friend")
  Friend create(@RequestBody Friend friend)  {
    return friendService.save(friend);
  }

  @GetMapping("/friend")
  Iterable<Friend> read() {
    return friendService.findAll();
  }

  @PutMapping("/friend")
  Friend update(@RequestBody Friend friend) {
    return friendService.save(friend);
  }

  @DeleteMapping("/friend/{id}")
  void delete(@PathVariable Integer id) {
    friendService.deleteById(id);
  }

  @GetMapping("/wrong")
  Friend somethingIsWrong() {
    throw new ValidationException("Something is wrong");
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ValidationException.class)
  String exceptionHandler(ValidationException e) {
    return e.getMessage();
  }
}
