package com.example.movielog.model.review;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.model.user.User;
import lombok.Builder;
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
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private User user;

  @JoinColumn(name = "MOVIE_NO")
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Movie movie;

  public Review(){}

  @Builder
  public Review(Movie movie, User user, String title, String content) {
    this.movie = movie;
    this.user = user;
    this.title = title;
    this.content = content;
  }

  public void update(String title, String content){
    this.title = title;
    this.content = content;
  }
}
