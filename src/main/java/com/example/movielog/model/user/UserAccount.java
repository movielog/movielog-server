package com.example.movielog.model.user;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
public class UserAccount extends User {

  private UserDetails user;

  public UserAccount(UserDetails user) {
    super(user.getUsername(), user.getPassword(), authorities(user));
    this.user = user;
  }

  private static Collection<? extends GrantedAuthority> authorities(UserDetails user){
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    return authorities;
  }
}