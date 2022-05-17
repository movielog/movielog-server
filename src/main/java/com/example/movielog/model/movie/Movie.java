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
  private Long no;

  private String title;
  private String subtitle;
  private String director;
  private String actor;
  private int pub_date;
  private int price;
  private String image;
  private double user_rating;

  @OneToMany(mappedBy = "movie")
  private List<Review> reviews = new ArrayList<>();

  public Movie(){}

  @Builder
  public Movie(String title, String subtitle, String director, String actor, int pub_date, int price, String image, double user_rating){}
}
