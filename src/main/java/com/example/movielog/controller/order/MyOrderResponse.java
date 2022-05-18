package com.example.movielog.controller.order;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.model.order.Order;
import com.example.movielog.model.order.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyOrderResponse {
  private Long orderId;
  private OrderStatus status;
  private LocalDateTime date;
  private String title;
  private int price;

  public MyOrderResponse(Order order, Movie movie){
    orderId = order.getId();
    status = order.getStatus();
    date = order.getDate();

    title = movie.getTitle();
    price = movie.getPrice();
  }
}
