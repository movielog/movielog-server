package com.example.movielog.model.order;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.model.user.User;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "ORDERS")
@Getter
@Entity
public class Order {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDER_NO")
  private Long id;

  @JoinColumn(name = "USER_NO")
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @JoinColumn(name = "MOVIE_NO")
  @ManyToOne(fetch = FetchType.LAZY)
  private Movie movie;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  private LocalDateTime date;

  public Order() {}

  public Order(User user, Movie movie){
    this.user = user;
    this.movie = movie;
    this.status = OrderStatus.ORDER;
    this.date = LocalDateTime.now();
  }
}
