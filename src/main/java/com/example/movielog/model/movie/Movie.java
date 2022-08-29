package com.example.movielog.model.movie;

import com.example.movielog.model.review.Review;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Movie {

  @Id @GeneratedValue
  @Column(name = "MOVIE_NO")
  private Long id;

  private String title;
  private String subtitle;
  private String director;
  private String actor;
  private int pub_date;
  private int price;
  private String image;
  private double user_rating;

  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
  private List<Review> reviews = new ArrayList<>();

  public Movie(){}

  @Builder
  public Movie(String title, String subtitle, String director, String actor, int pub_date, int price, String image, double user_rating){
    this.title = title;
    this.subtitle = subtitle;
    this.director = director;
    this.actor = actor;
    this.pub_date = pub_date;
    this.price = price;
    this.image = image;
    this.user_rating = user_rating;
  }
}
