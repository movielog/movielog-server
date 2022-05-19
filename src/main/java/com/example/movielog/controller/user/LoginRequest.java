package com.example.movielog.controller.user;

import com.example.movielog.model.user.User;

public class LoginRequest {
  private String email;
  private String password;

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public User newLogin(User user){
    return new User(user.getEmail(), user.getPassword());
  }
}
