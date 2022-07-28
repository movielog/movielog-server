package com.example.movielog.service;

import com.example.movielog.model.movie.Movie;
import com.example.movielog.repository.movie.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

  private final MovieRepository movieRepository;

  public MovieService(MovieRepository movieRepository){
    this.movieRepository = movieRepository;
  }

  @Transactional
  public Optional<Movie> findById(Long id){
    return movieRepository.findById(id);
  }

  @Transactional
  public List<Movie> findAll(){
    return movieRepository.findAll();
  }
}
