package com.example.movielog.controller.movie;

import com.example.movielog.model.movie.Movie;
import lombok.Getter;

@Getter
public class MovieDetailResponse {
  private Long movieId;
  private String title;
  private String subtitle;
  private String image;
  private String director;
  private String actor;
  private int price;
  private int pub_date;
  private double user_rating;

  public MovieDetailResponse(Movie movie){
    movieId = movie.getId();
    title = movie.getTitle();
    subtitle = movie.getSubtitle();
    image = movie.getImage();
    director = movie.getDirector();
    actor = movie.getActor();
    price = movie.getPrice();
    pub_date = movie.getPub_date();
    user_rating = movie.getUser_rating();
  }
}
