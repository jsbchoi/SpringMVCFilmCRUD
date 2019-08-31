package com.skilldistillery.film.dao;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	public Film getFilmByID(int filmID);
	public List<Film> getFilmsByKeyword(String keyword);
	public List<Actor> getActors(int filmID);
	public void addFilm(Film film);
	public void deleteFilm(int filmID);
	public void updateFilm(Film film);
}
