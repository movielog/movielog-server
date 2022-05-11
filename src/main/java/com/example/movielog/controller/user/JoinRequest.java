package com.example.movielog.controller.user;

public class JoinRequest {

  private String principal;

  private String credentials;

  private String nickname;

  protected JoinRequest() {}

  public JoinRequest(String principal, String credentials, String nickname){
    this.principal = principal;
    this.credentials = credentials;
    this.nickname = nickname;

  }

  public String getPrincipal() {
    return principal;
  }

  public String getCredentials() {
    return credentials;
  }

  public String getNickname() {
    return nickname;
  }

}
