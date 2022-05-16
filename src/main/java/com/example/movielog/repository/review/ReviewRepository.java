package com.example.movielog.repository.review;

import com.example.movielog.model.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
