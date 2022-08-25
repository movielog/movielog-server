package com.example.movielog.service;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.model.review.Review;
import com.example.movielog.model.user.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
public class ReviewServiceTest {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  ReviewService reviewService;

  @Test
  @Order(1)
  public void Review_작성하기(){
    Movie movie = new Movie("해리 포터와 마법사의 돌", "Harry Potter And The Sorcerer", "크리스 콜럼버스", "다니엘 래드클리프, 루퍼트 그린트, 엠마 왓슨", 2001, 10000, "",  9.36);
    User user = new User("sunny@gmail.com", "1234", "sunny");
    String title = "review title";
    String content = "review content";

    Review review = reviewService.write(new Review(movie, user, title, content));

    assertThat(review.getId(), is(notNullValue()));
    assertThat(review.getContent(), is(content));
    assertThat(review.getId(), is(1L));

    log.info("review : {}", review);
  }

  @Test
  @Order(2)
  public void Review_수정하기(){
    Movie movie = new Movie("해리 포터와 마법사의 돌", "Harry Potter And The Sorcerer", "크리스 콜럼버스", "다니엘 래드클리프, 루퍼트 그린트, 엠마 왓슨", 2001, 10000, "",  9.36);
    User user = new User("sunny@gmail.com", "1234", "sunny");
    String title = "review title";
    String content = "review content";

    Review review = reviewService.write(new Review(movie, user, title, content));

    String updateTitle = "update title";
    String updateContent = "update content";

    review.update(updateTitle, updateContent);

    assertThat(review, is(notNullValue()));
    assertThat(review.getTitle(), is(updateTitle));
    assertThat(review.getContent(), is(updateContent));
  }

  @Test
  @Order(3)
  public void Review_전체목록_조회하기(){
    List<Review> reviewList = reviewService.findAll();

    assertThat(reviewList, is(notNullValue()));
    assertThat(reviewList.size(), is(1));
  }
}
