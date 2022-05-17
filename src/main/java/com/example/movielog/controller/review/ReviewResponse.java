package com.example.movielog.controller.review;

import com.example.movielog.model.review.Review;
import lombok.Getter;

@Getter
public class ReviewResponse {

  private Long id;

  private String title;

  private String content;

  private String nickname;

  private String movietitle;

  public ReviewResponse(Review review) {
    id = review.getId();
    title = review.getTitle();
    content = review.getContent();
    nickname = review.getUser().getNickname();
    movietitle = review.getMovie().getTitle();
  }
}
