package com.example.movielog.controller.movie;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MovieRestController {

  private final MovieService movieService;

  public MovieRestController(MovieService movieService){
    this.movieService = movieService;
  }

  @GetMapping(value = "/movie")
  public ResponseEntity<List<MovieResponse>> main() {

    List<Movie> movieList = movieService.findAll();

    List<MovieResponse> collectList = movieList.stream()
            .map(MovieResponse::new)
            .collect(Collectors.toList());

    return ResponseEntity.ok().body(collectList);
  }

  @GetMapping("/movie/{id}")
  public ResponseEntity<MovieDetailResponse> findById(@PathVariable long id){

    Movie movie = movieService.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다"));

    MovieDetailResponse movieDetailResponse = new MovieDetailResponse(movie);

    return ResponseEntity.ok().body(movieDetailResponse);
  }
}
