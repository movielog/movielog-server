package com.example.movielog.controller.review;

import com.example.movielog.model.review.Review;
import lombok.Getter;

@Getter
public class ReviewResponse {

  private Long reviewId;

  private String title;

  private String content;

  private String nickname;

  private String movietitle;

  public ReviewResponse(Review review) {
    reviewId = review.getId();
    title = review.getTitle();
    content = review.getContent();
    nickname = review.getUser().getNickname();
    movietitle = review.getMovie().getTitle();
  }
}
