package com.example.movielog.controller.user;

import com.example.movielog.model.user.User;
import com.example.movielog.repository.user.UserRepository;
import com.example.movielog.security.JwtTokenProvider;
import com.example.movielog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserRestController {

  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  private final UserService userService;

  @PostMapping("/join")
  public Long join(@RequestBody Map<String, String> user){
    return userRepository.save(User.builder()
            .email(user.get("email"))
            .password(passwordEncoder.encode(user.get("password")))
            .nickname(user.get("nickname"))
            .roles(Collections.singletonList("ROLE_USER"))
            .build()).getId();
  }

  @PostMapping("/login")
  public String login(@RequestBody Map<String, String> user){
    User member = userRepository.findByEmail(user.get("email"))
            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 email 입니다."));

    if(!passwordEncoder.matches(user.get("password"), member.getPassword())){
      throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
    }
    return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
  }

}
