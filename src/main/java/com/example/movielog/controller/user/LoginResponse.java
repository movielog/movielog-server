package com.example.movielog.controller.user;

import lombok.Getter;

@Getter
public class LoginResponse {
  private String token;
  private String nickname;

  public LoginResponse(String token, String nickname){
    this.token = token;
    this.nickname = nickname;
  }
}
