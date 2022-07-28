package com.example.movielog.repository.order;

import com.example.movielog.model.order.Order;
import com.example.movielog.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findAllByUser(User user);
}
