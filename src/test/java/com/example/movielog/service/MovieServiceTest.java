package com.example.movielog.service;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.repository.movie.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class MovieServiceTest  {

  @Autowired
  MovieService movieService;

  @Autowired
  MovieRepository movieRepository;

  @BeforeEach
  public void createData(){

    // given
    movieRepository.save(
            Movie.builder()
                    .title("해리 포터와 마법사의 돌")
                    .subtitle("Harry Potter And The Sorcerer")
                    .director("크리스 콜럼버스")
                    .actor("다니엘 래드클리프, 루퍼트 그린트, 엠마 왓슨")
                    .pub_date(2001)
                    .price(10000)
                    .image("")
                    .user_rating(9.36)
                    .build()
    );

    movieRepository.save(
            Movie.builder()
                    .title("해리 포터와 비밀의 방")
                    .subtitle("Harry Potter And The Chamber Of Secrets")
                    .director("크리스 콜럼버스")
                    .actor("다니엘 래드클리프, 루퍼트 그린트, 엠마 왓슨")
                    .pub_date(2002)
                    .price(11000)
                    .image("")
                    .user_rating(8.9)
                    .build()
    );

    movieRepository.save(
            Movie.builder()
                    .title("해리 포터와 아즈카반의 죄수")
                    .subtitle("Harry Potter And The Prisoner Of Azkaban")
                    .director("알폰소 쿠아론")
                    .actor("다니엘 래드클리프, 루퍼트 그린트, 엠마 왓슨")
                    .pub_date(2004)
                    .price(12000)
                    .image("")
                    .user_rating(8.7)
                    .build()
    );
  }
  
  @Test
  public void Movie_1개_출력하기(){

    // when
    Movie movie1 = movieService.findById(1L).orElse(null);
    Movie movie2 = movieService.findById(2L).orElse(null);

    // then
    assertThat(movie1.getId(), is(1L));
    assertThat(movie2.getSubtitle(), is("Harry Potter And The Chamber Of Secrets"));
    assertThat(movie2.getPub_date(), is(2002));
  }

  @Test
  public void Movie_리스트_출력하기(){

    // when
    List<Movie> movieList = movieService.findAll();

    // then
    assertThat(movieList.size(), is(3));
  }
}
