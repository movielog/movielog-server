package com.example.movielog.controller.movie;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieRestController {

  private final MovieService movieService;

  public MovieRestController(MovieService movieService){
    this.movieService = movieService;
  }

  @GetMapping(value = "")
  public ResponseEntity<List<Movie>> main() {
    List<Movie> movieList = movieService.findAll();
    return ResponseEntity.ok().body(movieList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Movie> findById(@PathVariable long id){
    return ResponseEntity.of(movieService.findById(id));
  }
}
