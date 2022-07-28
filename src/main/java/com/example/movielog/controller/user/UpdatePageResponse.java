package com.example.movielog.controller.user;

import lombok.Getter;

@Getter
public class UpdatePageResponse {
  private String email;
  private String nickname;

  public UpdatePageResponse(String email, String nickname){
    this.email = email;
    this.nickname = nickname;
  }
}
