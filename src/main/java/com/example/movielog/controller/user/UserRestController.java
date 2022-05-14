package com.example.movielog.controller.user;

import com.example.movielog.model.user.User;
import com.example.movielog.model.user.UserAccount;
import com.example.movielog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationConverter;
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
  public ResponseEntity<User> updateMe(@AuthenticationPrincipal UserAccount userAccount,
                                              @RequestBody UserUpdateRequest userUpdateRequest){

    User user = userService.findByEmail(userAccount.getUsername())
            .orElseThrow(()-> new UsernameNotFoundException("x"));

    userService.update(user, userUpdateRequest.getNickname());
    return ResponseEntity.ok().body(user);
  }


}
