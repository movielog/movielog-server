package com.example.movielog.service;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.model.order.Order;
import com.example.movielog.model.user.User;
import com.example.movielog.repository.order.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository){
    this.orderRepository = orderRepository;
  }

  @Transactional
  public Order order(Order order){;
    return orderRepository.save(order);
  }

  @Transactional
  public List<Order> findAllByUser(User user){
    return orderRepository.findAllByUser(user);
  }

  @Transactional
  public Optional<Order> findById(Long id){
    return orderRepository.findById(id);
  }
}
