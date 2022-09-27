package com.example.movielog.security;

import com.example.movielog.model.user.UserAccount;
import com.example.movielog.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  // spring security에서 유저의 정보를 가져온다.

  private final UserRepository userRepository;

  @Autowired
  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserDetails user = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));

    return new UserAccount(user);
  }
}
