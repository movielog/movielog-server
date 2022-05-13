package com.example.movielog.controller.user;

import com.example.movielog.model.user.User;
import com.example.movielog.model.user.UserAccount;
import com.example.movielog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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


  @PostMapping("/user/me")
  public ResponseEntity<UserAccount> me(@AuthenticationPrincipal UserAccount userAccount){
    User user = new User(userAccount.getUsername(), userAccount.getPassword(), userAccount.getUsername());
    System.out.println(user.getEmail() + " " + user.getNickname());
    return ResponseEntity.ok().body(userAccount);
  }
}
