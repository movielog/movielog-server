package com.example.movielog.controller.review;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.model.review.Review;
import com.example.movielog.model.user.User;
import com.example.movielog.model.user.UserAccount;
import com.example.movielog.service.MovieService;
import com.example.movielog.service.ReviewService;
import com.example.movielog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/review")
@RestController
public class ReviewRestController {

  private final ReviewService reviewService;

  private final UserService userService;

  private final MovieService movieService;

  @PostMapping("/write/{movieId}")
  public Long write(@AuthenticationPrincipal UserAccount userAccount,
                                      @RequestBody ReviewRequest reviewRequest,
                                      @PathVariable("movieId") Long movieId){

    User user = userService.findByEmail(userAccount.getUsername())
          .orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 회원입니다."));

    Movie movie = movieService.findById(movieId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));

    return reviewService.write(reviewRequest
            .newReview(user, movie, reviewRequest.getTitle(), reviewRequest.getContent()))
            .getId();
  }

  @GetMapping("")
  public ResponseEntity<List<ReviewResponse>> reviews() {
    List<Review> reviewList = reviewService.findAll();

    List<ReviewResponse> collect = reviewList.stream()
            .map(ReviewResponse::new)
            .collect(Collectors.toList());

    return ResponseEntity.ok(collect);
  }

  @PostMapping("/update/{reviewId}")
  public Long update(@RequestBody ReviewUpdateRequest reviewUpdateRequest,
                     @PathVariable("reviewId") Long reviewId){
    Review review = reviewService.findById(reviewId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다"));

    reviewService.update(review, reviewUpdateRequest.getTitle(), reviewUpdateRequest.getContent());

    return reviewId;
  }

  @DeleteMapping("/{reviewId}")
  public void delete(@PathVariable("reviewId") Long reviewId){
    Review review = reviewService.findById(reviewId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다"));

    reviewService.delete(review);
  }

  @GetMapping("/my")
  public ResponseEntity<List<ReviewResponse>> myReviews(@AuthenticationPrincipal UserAccount userAccount){
    User user = userService.findByEmail(userAccount.getUsername())
            .orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 회원입니다."));

    List<Review> reviewList = reviewService.findAllByUser(user);

    List<ReviewResponse> collect = reviewList.stream()
            .map(ReviewResponse::new)
            .collect(Collectors.toList());

    return ResponseEntity.ok(collect);
  }
}
