package bsm_movie_ra.movies.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsm_movie_ra.movies.document.Movie;
import bsm_movie_ra.movies.repository.MovieRepository;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> movieById(ObjectId id) {
      return movieRepository.findById(id);
    }

    public Optional<Movie> movieByImdbId(String id) {
      return movieRepository.findByImdbId(id);
    }
    
}
