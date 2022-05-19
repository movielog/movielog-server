package com.example.movielog.controller.movie;

import com.example.movielog.model.movie.Movie;
import lombok.Getter;

@Getter
public class MovieResponse {
  private Long movieId;
  private String title;
  private String subtitle;
  private String image;
  private String director;

  public MovieResponse(Movie movie){
    movieId = movie.getId();
    title = movie.getTitle();
    subtitle = movie.getSubtitle();
    image = movie.getImage();
    director = movie.getDirector();
  }
}
