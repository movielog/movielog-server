package com.example.movielog.controller.order;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.model.order.Order;
import com.example.movielog.model.user.User;
import com.example.movielog.model.user.UserAccount;
import com.example.movielog.service.MovieService;
import com.example.movielog.service.OrderService;
import com.example.movielog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class OrderRestController {

  private final OrderService orderService;

  private final UserService userService;

  private final MovieService movieService;

  @PostMapping("/order/{movieId}")
  public Long order(@AuthenticationPrincipal UserAccount userAccount,
                    @PathVariable("movieId") Long movieId){

    User user = userService.findByEmail(userAccount.getUsername())
            .orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 회원입니다."));

    Movie movie = movieService.findById(movieId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));

    return orderService.order(new OrderRequest()
            .newOrder(user, movie))
            .getId();
  }

  @GetMapping("/order/{movieId}")
  public ResponseEntity<OrderPageResponse> orderPage(@PathVariable("movieId") Long movieId){
    Movie movie = movieService.findById(movieId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));

    OrderPageResponse orderPageResponse = new OrderPageResponse(movie);

    return ResponseEntity.ok().body(orderPageResponse);
  }

  @GetMapping("/my/order")
  public ResponseEntity<List<MyOrderResponse>> myOrderList(@AuthenticationPrincipal UserAccount userAccount){
    User user = userService.findByEmail(userAccount.getUsername())
            .orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 회원입니다."));

    List<Order> orderList = orderService.findAllByUser(user);

    List<MyOrderResponse> collectList = orderList.stream()
            .map(o -> new MyOrderResponse(o, o.getMovie()))
            .collect(Collectors.toList());

    return ResponseEntity.ok(collectList);
  }

  @GetMapping("/my/order/{orderId}")
  public ResponseEntity<MyOrderDetailResponse> myOrderDetail(@PathVariable("orderId") Long orderId){

    Order order = orderService.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다"));

    MyOrderDetailResponse orderDetailResponse = new MyOrderDetailResponse(order.getMovie(), order);

    return ResponseEntity.ok().body(orderDetailResponse);
  }
}
