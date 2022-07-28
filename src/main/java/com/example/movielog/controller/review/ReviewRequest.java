package com.example.movielog.controller.review;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.model.review.Review;
import com.example.movielog.model.user.User;

public class ReviewRequest {

  private String title;

  private String content;

  public String getTitle() { return title; }

  public String getContent(){ return content; }

  protected ReviewRequest() {}

  public Review newReview(User user, Movie movie, String title, String content){
    return new Review(movie, user, title, content);
  }
}
