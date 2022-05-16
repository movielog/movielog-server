package com.example.movielog.service;

import com.example.movielog.model.review.Review;
import com.example.movielog.repository.review.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

  private final ReviewRepository reviewRepository;

  public ReviewService(ReviewRepository reviewRepository){
    this.reviewRepository = reviewRepository;
  }

  @Transactional
  public Review write(Review review){
    return reviewRepository.save(review);
  }
}
