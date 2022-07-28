package com.example.movielog.controller.order;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.model.order.Order;
import com.example.movielog.model.user.User;

public class OrderRequest {
  public Order newOrder(User user, Movie movie){
    return new Order(user, movie);
  }
}
