package com.example.movielog.controller.order;

import com.example.movielog.model.movie.Movie;
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

import java.util.Optional;

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
}
