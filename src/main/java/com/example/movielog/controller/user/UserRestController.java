package com.example.movielog.controller.user;

import com.example.movielog.model.user.User;
import com.example.movielog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class UserRestController {

  private final UserService userService;

  @PostMapping("/join")
  public ResponseEntity<User> join(@RequestBody User user) {
    User joinUser = userService.join(user.getEmail(), user.getPassword(), user.getNickname());
    return ResponseEntity.ok().body(joinUser);
  }


  @PostMapping("/login")
  public String login(@RequestBody User user){
    return userService.login(user.getEmail(), user.getPassword());
  }
}
