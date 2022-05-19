package com.example.movielog.controller.user;

import com.example.movielog.model.user.User;
import com.example.movielog.model.user.UserAccount;
import com.example.movielog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserRestController {

  private final UserService userService;

  private final PasswordEncoder passwordEncoder;

  @PostMapping("/join")
  public ResponseEntity<User> join(@RequestBody JoinRequest joinRequest) {


    User joinUser = userService.join(joinRequest
            .newUser(joinRequest.getEmail(), passwordEncoder.encode(joinRequest.getPassword()), joinRequest.getNickname()));

    return ResponseEntity.ok().body(joinUser);
  }


  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
    User user = userService.findByEmail(loginRequest.getEmail())
            .orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 회원입니다."));

    String token = userService.login(loginRequest.getEmail(), loginRequest.getPassword());

    LoginResponse loginResponse = new LoginResponse(token, user.getNickname());

    return ResponseEntity.ok().body(loginResponse);
  }

  @GetMapping("/user/me")
  public ResponseEntity<UpdatePageResponse> updateMePage(@AuthenticationPrincipal UserAccount userAccount){
    User user = userService.findByEmail(userAccount.getUsername())
            .orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 회원입니다."));

    UpdatePageResponse updatePageResponse = new UpdatePageResponse(user.getEmail(), user.getNickname());

    return ResponseEntity.ok().body(updatePageResponse);
  }


  @PostMapping("/user/me")
  public ResponseEntity<User> updateMe(@AuthenticationPrincipal UserAccount userAccount,
                                       @RequestBody UserUpdateRequest userUpdateRequest){

    User user = userService.findByEmail(userAccount.getUsername())
            .orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 회원입니다."));

    userService.update(user, userUpdateRequest.getNickname());
    return ResponseEntity.ok().body(user);
  }

  @DeleteMapping("/user")
  public void delete(@AuthenticationPrincipal UserAccount userAccount){
    User user = userService.findByEmail(userAccount.getUsername())
            .orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 회원입니다."));

    userService.delete(user);
  }

}
