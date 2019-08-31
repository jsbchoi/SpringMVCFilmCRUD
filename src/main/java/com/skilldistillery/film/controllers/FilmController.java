package com.skilldistillery.film.controllers;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.dao.FilmDAOImpl;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	FilmDAO filmDAO = new FilmDAOImpl();
	
	@RequestMapping(path = "search.do", method = RequestMethod.GET, params= "filmID")
	public ModelAndView searchByID(int filmID) {
		Film film = filmDAO.getFilmByID(filmID);
		ModelAndView mv = new ModelAndView("WEB-INF/searchResults.jsp");
		mv.addObject(film);
		return mv;
	}
	
	@RequestMapping(path = "search.do", method = RequestMethod.GET, params= "keyword")
	public ModelAndView searchByID(String keyword) {
		List<Film> films = filmDAO.getFilmsByKeyword(keyword);
		ModelAndView mv = new ModelAndView("WEB-INF/searchResults.jsp");
		mv.addObject("filmList", films);
		return mv;
	}

}
