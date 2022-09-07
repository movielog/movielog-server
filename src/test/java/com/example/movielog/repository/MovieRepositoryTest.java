package com.example.movielog.repository;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.model.user.User;
import com.example.movielog.repository.movie.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MovieRepositoryTest {

  @Autowired
  private MovieRepository movieRepository;

  @Test
  @DisplayName("repo: findById 테스트")
  void findById(){

    // given
    Movie movie = movieRepository.save(
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

    // when
    Optional<Movie> actualMovie = movieRepository.findById(movie.getId());

    Movie actual = actualMovie.get();

    // then
    assertThat(movie.getId(), is(actual.getId()));
  }
}
