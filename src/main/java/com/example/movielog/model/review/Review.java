package com.example.movielog.model.review;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.model.user.User;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Review {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "REVIEW_NO")
  private Long id;

  private String title;

  private String content;

  @JoinColumn(name = "USER_NO")
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @JoinColumn(name = "MOVIE_NO")
  @ManyToOne(fetch = FetchType.LAZY)
  private Movie movie;

  public Review(){}

  public Review(Movie movie, User user, String title, String content) {
    this.movie = movie;
    this.user = user;
    this.title = title;
    this.content = content;
  }

  public Review(Long id, String title, String content, User user, Movie movie){
    this.id = id;
    this.title = title;
    this.content = content;
    this.user = user;
    this.movie = movie;
  }
}
