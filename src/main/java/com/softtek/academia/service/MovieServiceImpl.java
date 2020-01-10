package com.softtek.academia.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.academia.entity.Movie;
import com.softtek.academia.repository.MovieRepository;

@Service
@Transactional
public class MovieServiceImpl implements MovieService{


	@Autowired
	private MovieRepository repository;

	@Override
	public List<Movie> getAllMovies() {
		return (List<Movie>) repository.findAll();
	}

	public Movie getMovieById(final Integer id) {
		Movie movie = this.repository.findById(id).orElse(null);
		return movie;
	}

	public Movie addMovie(final Movie movie){
			return this.repository.save(movie);	
	}
	
	public Movie updateMovie(final Movie movie) {
		return repository.save(movie);	
	}
	
	@Override
	public boolean deleteMovieById(Integer id) {
		this.repository.deleteById(id);
		return true;
	}
	
	public Movie patchMovie(Integer id, Movie requestBody) {
		Movie movie = this.getMovieById(id);
		movie.setId(id);
		movie.setId(requestBody.getId() != null ? requestBody.getId() : movie.getId());
		movie.setTitle(requestBody.getTitle() != null ? requestBody.getTitle() : movie.getTitle());
		movie.setGenre_id(requestBody.getGenre_id() != null ? requestBody.getGenre_id() : movie.getGenre_id());
		movie.setDirector_id(requestBody.getDirector_id() != null ? requestBody.getDirector_id(): movie.getDirector_id());
		movie.setRating(requestBody.getRating() != null ? requestBody.getRating(): movie.getRating());
		return repository.save(movie);
	}

}
