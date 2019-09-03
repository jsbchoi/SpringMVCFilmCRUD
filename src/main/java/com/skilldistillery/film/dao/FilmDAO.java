package com.skilldistillery.film.dao;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	public Film getFilmByID(int filmID);
	public List<Film> getFilmsByKeyword(String keyword);
	public List<Actor> getActors(int filmID);
	public boolean addFilm(Film film);
	public boolean deleteFilm(int filmID);
	public boolean updateFilm(Film film);
	public String getCategoryOfFilm(int filmId);
	public String getLanguageOfFilm(int filmId);
	List<String> getInventoryStatusOfFilm(int filmId);
}
