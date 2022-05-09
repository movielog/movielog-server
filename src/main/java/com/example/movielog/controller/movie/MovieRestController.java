package com.example.movielog.controller.movie;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieRestController {

  private final MovieService movieService;

  public MovieRestController(MovieService movieService){
    this.movieService = movieService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Movie> findById(@PathVariable long id){
    return ResponseEntity.of(movieService.findById(id));
  }
}
