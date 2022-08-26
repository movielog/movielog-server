package com.example.movielog.controller.review;

public class ReviewUpdateRequest {
  private String title;
  private String content;

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public ReviewUpdateRequest(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
