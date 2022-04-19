package com.wildbeancoffee.friends.controllers;

import com.wildbeancoffee.friends.model.Friend;
import com.wildbeancoffee.friends.services.FriendService;
import com.wildbeancoffee.friends.util.ErrorMessage;
import com.wildbeancoffee.friends.util.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FriendController {

  @Autowired
  FriendService friendService;

  @PostMapping("/friend")
  Friend create(@Valid @RequestBody Friend friend)  {
       return friendService.save(friend);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e) {
    List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
    List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());

    return fieldErrorMessages;
  }

  @GetMapping("/friend")
  Iterable<Friend> read() {
    return friendService.findAll();
  }

  @PutMapping("/friend")
  ResponseEntity<Friend> update(@RequestBody Friend friend) {
    if(friendService.findById(friend.getId()).isPresent())
    return new ResponseEntity(friendService.save(friend), HttpStatus.OK);
    else
      return new ResponseEntity(friend, HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping("/friend/{id}")
  void delete(@PathVariable Integer id) {
    friendService.deleteById(id);
  }
}
