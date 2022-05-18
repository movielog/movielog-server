package com.example.movielog.controller.order;

import com.example.movielog.model.movie.Movie;
import lombok.Getter;

@Getter
public class OrderPageResponse {
  private String title;
  private String subtitle;
  private String image;
  private int price;

  public OrderPageResponse(Movie movie){
    title = movie.getTitle();
    subtitle = movie.getSubtitle();
    image = movie.getImage();
    price = movie.getPrice();
  }
}
