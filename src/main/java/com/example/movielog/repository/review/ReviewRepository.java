package com.example.movielog.repository.review;

import com.example.movielog.model.review.Review;
import com.example.movielog.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  List<Review> findAllByUser(User user);
}
