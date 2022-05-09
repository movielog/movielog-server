package com.example.movielog.model.movie;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Movie {

  @Id @GeneratedValue
  private Long no;

  private String title;
  private String subtitle;
  private String director;
  private String actor;
  private int pub_date;
  private int price;
  private String image;
  private double user_rating;

  public Movie(){}

  @Builder
  public Movie(String title, String subtitle, String director, String actor, int pub_date, int price, String image, double user_rating){}
}
