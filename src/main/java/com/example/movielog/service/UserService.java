package com.example.movielog.service;

import com.example.movielog.model.user.User;
import com.example.movielog.repository.user.UserRepository;
import com.example.movielog.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final JwtTokenProvider jwtTokenProvider;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider){
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Transactional
  public User join(String email, String password, String nickname){
    return userRepository.save(User.builder()
            .email(email)
            .password(passwordEncoder.encode(password))
            .nickname(nickname)
            .roles(Collections.singletonList("ROLE_USER"))
            .build());
  }

  @Transactional
  public String login(String email, String password){
    User user = userRepository.findByEmail(email)
            .orElseThrow(()-> new IllegalArgumentException("가입되지 않은 email입니다."));

    if(!passwordEncoder.matches(password, user.getPassword())){
      throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
    }

    return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
  }

  @Transactional
  public void update(User user, String nickname){
    user.update(nickname);
    userRepository.save(user);
//    login(user.getEmail(), user.getPassword());
  }

  @Transactional
  public void delete(User user){
    userRepository.delete(user);
  }

  @Transactional
  public Optional<User> findByEmail(String email){
    return userRepository.findByEmail(email);
  }
}
