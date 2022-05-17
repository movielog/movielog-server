package com.example.movielog.service;

import com.example.movielog.controller.review.ReviewRequest;
import com.example.movielog.controller.review.ReviewResponse;
import com.example.movielog.model.review.Review;
import com.example.movielog.repository.review.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

  @Transactional
  public Optional<Review> findById(Long id){
    return reviewRepository.findById(id);
  }

  @Transactional
  public List<Review> findAll(){
    return reviewRepository.findAll();
  }

  @Transactional
  public Long update(Review review, String title, String content){
    review.update(title, content);
    return reviewRepository.save(review).getId();
  }
}
