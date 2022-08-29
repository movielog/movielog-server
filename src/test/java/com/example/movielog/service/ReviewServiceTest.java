package com.example.movielog.service;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.model.review.Review;
import com.example.movielog.model.user.User;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
public class ReviewServiceTest {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  ReviewService reviewService;

  @Autowired
  UserService userService;

  Movie movie, movie2;
  User user, user2;

  @BeforeEach
  public void createData(){
    movie = new Movie("해리 포터와 마법사의 돌", "Harry Potter And The Sorcerer", "크리스 콜럼버스", "다니엘 래드클리프, 루퍼트 그린트, 엠마 왓슨", 2001, 10000, "",  9.36);
    user = new User("sunny@gmail.com", "1234", "sunny");

    movie2 = new Movie("해리 포터와 비밀의 방", "Harry Potter And The Chamber Of Secrets", "크리스 콜럼버스", "다니엘 래드클리프, 루퍼트 그린트, 엠마 왓슨", 2002, 10500, "",8.9);
    user2 = new User("cloud@gmail.com", "5678", "cloud");
  }

  @Test
  @Order(1)
  public void Review_작성하기(){

    //given
    String title = "review title";
    String content = "review content";

    // when
    Review review = reviewService.write(new Review(movie, user, title, content));

    // then
    assertThat(review.getId(), is(notNullValue()));
    assertThat(review.getContent(), is(content));
    assertThat(review.getId(), is(1L));

    log.info("review : {}", review);
  }

  @Test
  @Order(2)
  @Transactional
  public void Review_수정하기(){

    // given
    String title = "review title";
    String content = "review content";

    Review review = reviewService.write(new Review(movie, user, title, content));

    // when
    String updateTitle = "update title";
    String updateContent = "update content";

    review.update(updateTitle, updateContent);

    // then
    assertThat(review, is(notNullValue()));
    assertThat(review.getTitle(), is(updateTitle));
    assertThat(review.getContent(), is(updateContent));
  }

  @Test
  @Order(3)
  public void Review_전체목록_조회하기(){

    // given
    String title = "review title";
    String content = "review content";

    reviewService.write(new Review(movie, user, title, content));

    // when
    List<Review> reviewList = reviewService.findAll();

    // then
    assertThat(reviewList, is(notNullValue()));
    assertThat(reviewList.size(), is(1));
  }

  @Test
  @Order(4)
  void Review_유저별_목록_조회하기(){

    // given
    String title = "review title";
    String content = "review content";

    reviewService.write(new Review(movie, user, title, content));
    reviewService.write(new Review(movie2, user2, title, content));

    // when
    List<Review> reviewList = reviewService.findAllByUser(user);

    // then
    assertThat(reviewList, is(notNullValue()));
    assertThat(reviewList.size(), is(1));
  }

  @Test
  void Review_삭제하기(){

    //given
    String title = "review title";
    String content = "review content";

    Review review = reviewService.write(new Review(movie, user, title, content));

    Review findReview = reviewService.findById(review.getId()).orElse(null);

    // when
    reviewService.delete(findReview);

    List<Review> reviewList = reviewService.findAllByUser(user);

    // then
    assertThat(reviewList.size(), is(0));
  }
}
