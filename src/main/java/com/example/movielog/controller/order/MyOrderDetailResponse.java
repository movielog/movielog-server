package com.example.movielog.controller.order;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.model.order.Order;
import com.example.movielog.model.order.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyOrderDetailResponse {
  private String title;
  private int price;
  private String image;

  private LocalDateTime date;
  private OrderStatus status;

  public MyOrderDetailResponse(Movie movie, Order order){
    title = movie.getTitle();
    price = movie.getPrice();
    image = movie.getImage();
    date = order.getDate();
    status = order.getStatus();
  }
}
