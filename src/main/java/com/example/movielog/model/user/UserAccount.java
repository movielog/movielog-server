package com.example.movielog.model.user;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


@Getter
public class UserAccount extends User {

//  private com.example.movielog.model.user.User account;
//
//  public UserAccount(com.example.movielog.model.user.User account) {
//    super(account.getEmail(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
//    this.account = account;
//  }

  private UserDetails user;

  public UserAccount(UserDetails user) {
    super(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
    this.user = user;
  }
}